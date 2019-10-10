package com.javasampleapproach.webflux.repo.impl;


import com.javasampleapproach.webflux.model.JobClient;
import com.javasampleapproach.webflux.repo.JobRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


    @Repository
    public class JobRepositoryImpl implements JobRepository {

        private Map<Long, JobClient> jobStores = new HashMap<Long, JobClient>();

        @PostConstruct
        public void initIt() throws Exception {
            jobStores.put(Long.valueOf(1), new JobClient(1, "Software Engineer", "5", false));
            jobStores.put(Long.valueOf(1), new JobClient(2, "Cleaner", "2", false));
            jobStores.put(Long.valueOf(1), new JobClient(3, "Barman", "7", false));
            jobStores.put(Long.valueOf(1), new JobClient(4, "Fireman", "4", false));
            jobStores.put(Long.valueOf(1), new JobClient(5, "Head of HR", "8", true));
            jobStores.put(Long.valueOf(2), new JobClient(6, "Team Lead", "3", true));
            jobStores.put(Long.valueOf(2), new JobClient(7, "CEO", "10", true));
        }

        @Override
        public Mono<JobClient> getJobById(Long id) {
            return Mono.just(jobStores.get(id));
        }

        @Override
        public Flux<JobClient> getAllJobs() {
            return Flux.fromIterable(this.jobStores.values());
        }

        @Override
        public Mono<Void> saveJob(Mono<JobClient> jobClient) {
            Mono<JobClient> customerMono = jobClient.doOnNext(job -> {
                // do post
                jobStores.put(job.getJobId(), job);
                // log on console
                System.out.println("########### POST:" + job);
            });

            return customerMono.then();
        }


        @Override
        public Mono<JobClient> putJob(Long id, Mono<JobClient> monoJob) {
            Mono<JobClient> jobMono = monoJob.doOnNext(job -> {
                // reset customer.Id
                job.setJobId(id);

                // do put
                jobStores.put(id, job);

                // log on console
                System.out.println("########### PUT:" + job);
            });

            return jobMono;
        }

        @Override
        public Mono<String> deleteJob(Long id) {
            // delete processing
            jobStores.remove(id);
            return Mono.just("this Job Deleted Succesfully!");
        }
    }

