package org.encog.ml.prg.train.selection;

import org.encog.mathutil.randomize.RangeRandomizer;
import org.encog.ml.genetic.GeneticAlgorithm;
import org.encog.ml.genetic.genome.Genome;
import org.encog.ml.genetic.population.Population;
import org.encog.ml.prg.train.PrgGenetic;

public class TournamentSelection implements PrgSelection {
	private GeneticAlgorithm trainer;
	private int rounds;
	
	public TournamentSelection(GeneticAlgorithm theTrainer, int theRounds) {
		this.trainer = theTrainer;
		this.rounds = theRounds;
	}

	

	@Override
	public GeneticAlgorithm getTrainer() {
		return trainer;
	}




	public void setTrainer(PrgGenetic trainer) {
		this.trainer = trainer;
	}




	public int getRounds() {
		return rounds;
	}



	public void setRounds(int rounds) {
		this.rounds = rounds;
	}



	@Override
	public int performSelection() {
		Population population = trainer.getPopulation();
		int bestIndex = RangeRandomizer.randomInt(0, population.size()-1);
	    Genome best = population.get(bestIndex);
	    this.trainer.calculateEffectiveScore(best);
	    
	    for ( int i = 0; i < this.rounds; i ++ ) {
	    	int competitorIndex = RangeRandomizer.randomInt(0, population.size()-1);
	      Genome competitor = population.get(competitorIndex);
	      this.trainer.calculateEffectiveScore(competitor);
	      if ( this.trainer.isGenomeBetter(competitor, best) ) {
	        best = competitor;
	        bestIndex = competitorIndex;
	      }
	    }
	    return( bestIndex );
	}


	@Override
	public int performAntiSelection() {
		Population population = trainer.getPopulation();
		int worstIndex = RangeRandomizer.randomInt(0, population.size()-1);
	    Genome worst = population.get(worstIndex);
	    this.trainer.calculateEffectiveScore(worst);
	    
	    for ( int i = 0; i < this.rounds; i ++ ) {
	    	int competitorIndex = RangeRandomizer.randomInt(0, population.size()-1);
	      Genome competitor = population.get(competitorIndex);
	      this.trainer.calculateEffectiveScore(competitor);
	      if ( !this.trainer.isGenomeBetter(competitor, worst) ) {
	        worst = competitor;
	        worstIndex = competitorIndex;
	      }
	    }
	    return( worstIndex );
	}
	
	
}
