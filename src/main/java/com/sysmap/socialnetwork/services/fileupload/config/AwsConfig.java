package com.sysmap.socialnetwork.services.fileupload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsConfig {
	
	@Bean
	protected AmazonS3 amazonS3() {
		return AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("mykey", "mykey")))
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://s3.localhost.localstack.cloud:4566", Regions.US_WEST_2.getName()))
				.build();
	}
	
}
