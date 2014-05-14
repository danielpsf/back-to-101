package net.carlosgsouza.backto101.class01

import spock.lang.Specification

public class HashSetAndTreeSetPerformanceAnalysis extends Specification {
	
	def "Is it faster to add elements to HashSet or TreeSet?"() {
		given: "Set and a TreeSet"
		HashSet<Integer> hashSetList
		TreeSet<Integer> treeSetList
		
		and: "the test parameters"
		int ROUNDS = 10
		int MEASUREMENTS = 100
		int ITENS_PER_MEASUREMENT = 10000
		
		and: "arrays to store the measurements"
		int[] hashSetListMeasurements = new int[MEASUREMENTS + 1]
		int[] treeSetListMeasurements = new int[MEASUREMENTS + 1]
		
		when: "measuring the performance of a HashSet"
		ROUNDS.times {
			long start = System.currentTimeMillis()
			
			hashSetList = new HashSet<Integer>()
			
			MEASUREMENTS.times { i -> 
				hashSetListMeasurements[i] += System.currentTimeMillis() - start
				
				ITENS_PER_MEASUREMENT.times { 
					hashSetList.add(i)	
				}
			}
			hashSetListMeasurements[MEASUREMENTS] += System.currentTimeMillis() - start
		}
		
		and: "measuring the performance of a TreeSet"
		ROUNDS.times {
			long start = System.currentTimeMillis()
			
			treeSetList = new TreeSet<Integer>()
			
			MEASUREMENTS.times { i ->
				treeSetListMeasurements[i] += System.currentTimeMillis() - start
				
				ITENS_PER_MEASUREMENT.times {
					treeSetList.add(i)
				}
			}
			treeSetListMeasurements[MEASUREMENTS] += System.currentTimeMillis() - start
		}
		
		then: "print the results"
		
		println "i\tSet\tTreeSet"
		(MEASUREMENTS + 1).times {
			def numberOfItems = it * ITENS_PER_MEASUREMENT
			def hashSetListTime = hashSetListMeasurements[it] / ROUNDS
			def treeSetListTime = treeSetListMeasurements[it] / ROUNDS
			
			println "$numberOfItems\t$hashSetListTime\t$treeSetListTime"
		}
		
		and: "answer the question"
		hashSetListMeasurements[MEASUREMENTS] < treeSetListMeasurements[MEASUREMENTS]
		
	}
}
