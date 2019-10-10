package com.javasampleapproach.webflux.repo;

import com.javasampleapproach.webflux.model.JobClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface JobRepository {
    public Mono<JobClient> getJobById(Long id);
    public Flux<JobClient> getAllJobs();
    public Mono<Void> saveJob(Mono<JobClient> job);
    public Mono<JobClient> putJob(Long id, Mono<JobClient> job);
    public Mono<String> deleteJob(Long id);
}
