package com.javasampleapproach.webflux.functional.handler;
import com.javasampleapproach.webflux.model.JobClient;
import com.javasampleapproach.webflux.repo.JobRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import org.springframework.http.MediaType;

import com.javasampleapproach.webflux.model.Customer;
import com.javasampleapproach.webflux.repo.CustomerRepository;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
// класс которым управляет спринг для обработки запросов

@Component
public class JobHandler {

    private final JobRepository jobRepository;

    public JobHandler(JobRepository repository) {
        this.jobRepository = repository;
    }

    /**
     * GET ALL Jobs
     */
    public Mono<ServerResponse> getAll(ServerRequest request) {
        // fetch all customers from repository
        Flux<JobClient> jobs = jobRepository.getAllJobs();

        // build response
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(jobs, JobClient.class);
    }

    /**
     * GET a Job by ID
     */
    public Mono<ServerResponse> getJob(ServerRequest request) {
        // parse path-variable
        long jobId = Long.valueOf(request.pathVariable("id"));

        // build notFound response
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        // get customer from repository
        Mono<JobClient> jobMono = jobRepository.getJobById(jobId);

        // build response
        return jobMono
                .flatMap(job -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(job)))
                .switchIfEmpty(notFound);
    }

    /**
     * POST a Job
     */
    public Mono<ServerResponse> postJob(ServerRequest request) {
        Mono<JobClient> job = request.bodyToMono(JobClient.class);
        return ServerResponse.ok().build(jobRepository.saveJob(job));
    }

    /**
     * PUT a Job
     */
    public Mono<ServerResponse> putJob(ServerRequest request) {
        // parse id from path-variable
        long jobId = Long.valueOf(request.pathVariable("id"));

        // get customer data from request object
        Mono<JobClient> job = request.bodyToMono(JobClient.class);

        // get customer from repository
        Mono<JobClient> responseMono = jobRepository.putJob(jobId, job);

        // build response
        return responseMono
                .flatMap(job1 -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(job1)));
    }

    /**
     * DELETE a Job
     */
    public Mono<ServerResponse> deleteJob(ServerRequest request) {
        // parse id from path-variable
        long jobId = Long.valueOf(request.pathVariable("id"));

        // get customer from repository
        Mono<String> responseMono = jobRepository.deleteJob(jobId);

        // build response
        return responseMono
                .flatMap(strMono -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(fromObject(strMono)));
    }
}
