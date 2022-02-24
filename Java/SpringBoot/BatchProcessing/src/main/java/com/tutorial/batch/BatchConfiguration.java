package com.tutorial.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Client> reader() {
        return new FlatFileItemReaderBuilder<Client>()
                .name("clientItemReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .delimited()
                .names(new String[] {
                        "idParty",
                        "commercialBrand",
                        "contactMediumType",
                        "contactMediumValue",
                        "verified" })
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Client>() {
                    {
                        setTargetType(Client.class);
                    }
                }).build();
    }

    @Bean
    public ClientItemProcessor processor() {
        return new ClientItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Client> writer(DataSource dataSource) {

        String query = "INSERT INTO client "
                + "(idParty, commercialBrand, contactMediumType, contactMediumValue, verified)"
                + " VALUES "
                + "(:idParty, :commercialBrand, :contactMediumType, :contactMediumValue, :verified)";

        return new JdbcBatchItemWriterBuilder<Client>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(query)
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importClientJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importClientJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Client> writer) {
        return stepBuilderFactory.get("step1")
                .<Client, Client>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}
