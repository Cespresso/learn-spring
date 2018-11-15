package cespresso.gmail.com.todo.batch

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Configuration
@EnableBatchProcessing
class BatchConfiguration(val jobBuilderFactory: JobBuilderFactory, val stepBuilderFactory: StepBuilderFactory) {
    @Component
    class Tasklet1:Tasklet{
        override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
            System.out.print("some")
            return RepeatStatus.FINISHED
        }

    }
    @Bean
    fun step1(): Step? = stepBuilderFactory.get("step1")?.tasklet(Tasklet1())?.build()

    // ジョブの中にステップがあってタスクを順に実行する
    @Bean
    fun job1(): Job? = jobBuilderFactory.get("job1")?.start(step1()!!)?.build()//!!の使用危険
}