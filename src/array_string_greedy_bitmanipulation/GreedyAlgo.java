package array_string_greedy_bitmanipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class GreedyAlgo {
	public static void main(String[] args) {
		Activity[] act = new Activity[6];
		act[0] = new Activity(0, 6);
		act[1] = new Activity(1, 2);
		act[2] = new Activity(8, 9);
		act[3] = new Activity(5, 7);
		act[4] = new Activity(5, 9);
		act[5] = new Activity(3, 4);
		System.out.println(activitySelectionProblem(act));

		ArrayList<Job> jobs = new ArrayList<>();
		jobs.add(new Job('a', 2, 100));
		jobs.add(new Job('b', 1, 19));
		jobs.add(new Job('c', 2, 27));
		jobs.add(new Job('d', 1, 25));
		jobs.add(new Job('e', 3, 15));

		System.out.println(jobSequencing1(jobs));
		System.out.println(jobSequencing2(jobs));
	}

	/*
	 * Job-sequencing problem
	 * 
	 * Given an array of jobs where every job has a deadline and associated profit
	 * if the job is finished before the deadline. It is also given that every job
	 * takes a single unit of time, so the minimum possible deadline for any job is
	 * 1. Maximize the total profit if only one job can be scheduled at a time.
	 */
	private static class Job {
		private char id;
		private int deadline;
		private int profit;

		Job(char i, int dead, int prof) {
			this.id = i;
			this.deadline = dead;
			this.profit = prof;
		}

		@Override
		public String toString() {
			return id + " " + deadline + " " + profit;
		}
	}

	public static ArrayList<Job> jobSequencing2(ArrayList<Job> jobList) {
		int n = jobList.size();
		ArrayList<Job> result = new ArrayList<>();
		PriorityQueue<Job> maxHeap = new PriorityQueue<>((a, b) -> b.profit - a.profit);

		Collections.sort(jobList, (a, b) -> a.deadline - b.deadline);
		for (int i = n - 1; i > -1; i--) {
			int slotAvailable;
			maxHeap.add(jobList.get(i));
			if (i == 0)
				slotAvailable = jobList.get(i).deadline;
			else
				slotAvailable = jobList.get(i).deadline - jobList.get(i - 1).deadline;

			while ((slotAvailable > 0) && (maxHeap.size() > 0)) {
				result.add(maxHeap.remove());
				slotAvailable--;
			}
		}

		Collections.sort(result, (a, b) -> a.deadline - b.deadline);
		return result;
	}

	public static ArrayList<Job> jobSequencing1(ArrayList<Job> jobList) {
		int n = jobList.size();
		ArrayList<Job> result = new ArrayList<>();
		boolean slot[] = new boolean[n];

		Collections.sort(jobList, (a, b) -> b.profit - a.profit);
		for (int i = 0; i < n; i++) {
			for (int j = Math.min(n - 1, jobList.get(i).deadline - 1); j >= 0; j--) {
				if (!slot[j]) {
					result.add(jobList.get(i));
					slot[j] = true;
					break;
				}
			}
		}
		Collections.sort(result, (a, b) -> a.deadline - b.deadline);
		return result;
	}

	/*
	 * You are given n activities with their start and finish times. Select the
	 * maximum number of activities that can be performed by a single person,
	 * assuming that a person can only work on a single activity at a time.
	 * 
	 * Example: Consider the following 6 activities sorted by finish time. start[] =
	 * {1, 3, 0, 5, 8, 5}; finish[] = {2, 4, 6, 7, 9, 9}; A person can perform at
	 * most four activities. The maximum set of activities that can be executed is
	 * {0, 1, 3, 4} [ These are indexes in start[] and finish[] ]
	 */
	private static class Activity implements Comparable<Activity> {
		private int start, finish;

		Activity(int s, int f) {
			this.start = s;
			this.finish = f;
		}

		@Override
		public int compareTo(Activity o) {
			if (this.start == o.start)
				return this.finish - o.finish;
			return this.start - o.start;
		}

		@Override
		public String toString() {
			return start + " - " + finish;
		}

	}

	public static int activitySelectionProblem(Activity[] act) {
		Arrays.sort(act, new Comparator<Activity>() {
			@Override
			public int compare(Activity o1, Activity o2) {
				if (o1.finish == o2.finish)
					return o1.start - o2.start;
				return o1.finish - o2.finish;
			}
		});
		int task = 1;
		int previousTask = 0;
		for (int i = 1; i < act.length; i++) {
			if (act[i].start >= act[previousTask].finish) {
				task++;
				previousTask = i;
			}
		}
		return task;
	}
}
