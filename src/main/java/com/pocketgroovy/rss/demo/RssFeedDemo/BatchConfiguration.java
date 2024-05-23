//package com.pocketgroovy.rss.demo.RssFeedDemo;
//
//import javax.sql.DataSource;
//
//import com.pocketgroovy.rss.demo.RssFeedDemo.model.Feed;
//import com.pocketgroovy.rss.demo.RssFeedDemo.read.RSSFeedParser;
//import com.pocketgroovy.rss.demo.RssFeedDemo.listner.JobCompletionNotificationListener;
//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.security.AnyTypePermission;
//import com.thoughtworks.xstream.security.NoTypePermission;
//import com.thoughtworks.xstream.security.NullPermission;
//import com.thoughtworks.xstream.security.PrimitiveTypePermission;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.database.JdbcBatchItemWriter;
//import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
//import org.springframework.batch.item.xml.StaxEventItemReader;
//import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.oxm.xstream.XStreamMarshaller;
//
//import java.io.InputStream;
//import java.util.*;
//
//@Configuration
//public class BatchConfiguration {
//
//    @Bean
//    public StaxEventItemReader reader() throws Exception {
//        RSSFeedParser parser = new RSSFeedParser(
//                "https://rss.nytimes.com/services/xml/rss/nyt/World.xml");
//        InputStream in = parser.read();
//        return new StaxEventItemReaderBuilder<Feed>()
//                .name("itemReader")
//                .resource(new InputStreamResource(in))
//                .addFragmentRootElements("item")
//                .unmarshaller(tradeMarshaller())
//                .build();
//
//    }
//    @Bean
//    public XStreamMarshaller tradeMarshaller() {
//
////        xstream.addPermission(NoTypePermission.NONE); //forbid everything/
////        xstream.addPermission(NullPermission.NULL);   // allow "null"
////        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
//        Class<?>[] classes = new Class[] {  com.pocketgroovy.rss.demo.RssFeedDemo.model.Feed.class };
//        XStream xstream = new XStream();
//        XStream.setupDefaultSecurity(xstream);
//        xstream.allowTypes(classes);
//
//        Map<String, Class> aliases = new HashMap<>();
//        aliases.put("item", Feed.class);
//        aliases.put("description", String.class);
////        aliases.put("price", BigDecimal.class);
////        aliases.put("isin", String.class);
////        aliases.put("customer", String.class);
////        aliases.put("quantity", Long.class);
//
//        XStreamMarshaller marshaller = new XStreamMarshaller();
//
//        marshaller.setAliases(aliases);
//
//        return marshaller;
//    }
//    @Bean
//    public FeedItemProcessor processor(){
//        return new FeedItemProcessor();
//    }
//
//    @Bean
//    public ItemWriter<Feed> writer(DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<Feed>()
//                .sql("INSERT INTO feeds (title, link, description, language, copyright) VALUES (:title, :link, :description, :language, :copyright)")
//                .dataSource(dataSource)
//                .beanMapped()
//                .build();
//    }
//
//    @Bean
//    public Job importFeedsJob(JobRepository jobRepository, Step step, JobCompletionNotificationListener listener) {
//        return new JobBuilder("importFeedsJob", jobRepository)
//                .listener(listener)
//                .start(step)
//                .build();
//    }
//
//    @Bean
//    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager,
//                      ItemReader<Feed> reader, FeedItemProcessor processor, JdbcBatchItemWriter<Feed> writer) {
//        return new StepBuilder("step1", jobRepository)
//                .<Feed, Feed> chunk(1, transactionManager)
//                .reader(reader)
//                .processor(processor)
//                .writer(writer)
//                .build();
//    }
//
//}
