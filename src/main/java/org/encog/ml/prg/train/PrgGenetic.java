package org.encog.ml.prg.train;

import java.util.Collections;
import java.util.List;

import org.encog.ml.MLMethod;
import org.encog.ml.TrainingImplementationType;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.genetic.MultiThreadedGeneticAlgorithm;
import org.encog.ml.genetic.genome.CalculateGenomeScore;
import org.encog.ml.prg.EncogProgramContext;
import org.encog.ml.train.MLTrain;
import org.encog.ml.train.strategy.Strategy;
import org.encog.neural.networks.training.TrainingSetScore;
import org.encog.neural.networks.training.genetic.GeneticScoreAdapter;
import org.encog.neural.networks.training.propagation.TrainingContinuation;
import org.encog.util.concurrency.MultiThreadable;

public class PrgGenetic extends MultiThreadedGeneticAlgorithm implements MLTrain, MultiThreadable {
	private final EncogProgramContext context;

	public PrgGenetic(PrgPopulation thePopulation,
			CalculateGenomeScore theScoreFunction) {
		super( thePopulation, theScoreFunction);
		this.context = thePopulation.getContext();	
		this.setParams(thePopulation.getContext().getParams());
	}


	public PrgGenetic(PrgPopulation thePopulation, MLDataSet theTrainingSet) {
		this(thePopulation, new GeneticScoreAdapter(new TrainingSetScore(theTrainingSet)));
	}

	@Override
	public TrainingImplementationType getImplementationType() {
		// TODO Auto-generated method stub
		return TrainingImplementationType.Background;
	}

	@Override
	public boolean isTrainingDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MLDataSet getTraining() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public void iteration(int count) {
		// TODO Auto-generated method stub

	}
	
	

	@Override
	public boolean canContinue() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TrainingContinuation pause() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resume(TrainingContinuation state) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addStrategy(Strategy strategy) {
		// TODO Auto-generated method stub

	}

	@Override
	public MLMethod getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Strategy> getStrategies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setError(double error) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setIteration(int iteration) {
		// TODO Auto-generated method stub

	}
	

	public void sort() {
		Collections.sort(this.getPopulation().getGenomes(), this.getCompareScore());

	}
	
	public PrgPopulation getPrgPopulation() {
		return (PrgPopulation)getPopulation();
	}

	/**
	 * @return the context
	 */
	public EncogProgramContext getContext() {
		return context;
	}
	
	@Override
	public int getMaxIndividualSize() {
		return ((PrgPopulation)this.getPopulation()).getHolder().getMaxIndividualSize();
	}
}
