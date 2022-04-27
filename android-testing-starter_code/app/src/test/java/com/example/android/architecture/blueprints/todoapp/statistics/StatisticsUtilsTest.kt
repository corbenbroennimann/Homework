package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest {

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        // Create an active task
        val tasks = listOf<Task>(
            Task("title", "desc", isCompleted = false)
        )

        // Call your function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(result.completedTasksPercent, 0f)
        assertEquals(result.activeTasksPercent, 100f)

        /*
        assertThat(result.completedTasksPercent, 'is' (0f))
        assertThat(result.activeTasksPercent, 'is' (100f))
        */
    }
    @Test
    fun getActiveAndCompletedStats_OneCompleted_returnsZeroHundred() {
        // Create an active task
        val tasks = listOf<Task>(
            Task("title", "desc", isCompleted = true)
        )
        // Call your function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(result.completedTasksPercent, 100f)
        assertEquals(result.activeTasksPercent, 0f)
    }

    @Test
    fun getActiveAndCompletedStats_TwoCompleted_ThreeActive_returnsFortySixty() {
        val tasks = listOf<Task>(
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false)
        )
        val result = getActiveAndCompletedStats(tasks)

        assertEquals(result.completedTasksPercent, 40f)
        assertEquals(result.activeTasksPercent, 60f)

    }

    @Test
    fun getActiveAndCompletedStats_empty_returnsZeroZero() {
        val result = getActiveAndCompletedStats(emptyList())

        assertEquals(result.completedTasksPercent, 0f)
        assertEquals(result.activeTasksPercent, 0f)

    }
    @Test
    fun getActiveAndCompletedStats_null_returnsZeroZero() {
        val result = getActiveAndCompletedStats(null)

        assertEquals(result.completedTasksPercent, 0f)
        assertEquals(result.activeTasksPercent, 0f)

    }

}