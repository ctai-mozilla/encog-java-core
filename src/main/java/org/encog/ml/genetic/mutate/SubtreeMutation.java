package org.encog.ml.genetic.mutate;

import java.util.Random;

import org.encog.ml.genetic.evolutionary.EvolutionaryOperator;
import org.encog.ml.genetic.genome.Genome;
import org.encog.ml.prg.EncogProgram;
import org.encog.ml.prg.EncogProgramContext;
import org.encog.ml.prg.train.CreateRandom;

public class SubtreeMutation implements EvolutionaryOperator {

	private CreateRandom rnd;
	
	public SubtreeMutation(EncogProgramContext theContext, int theMaxDepth) {
		this.rnd = new CreateRandom(theContext, theMaxDepth);
	}

	@Override
	public void performOperation(Random rnd, Genome[] parents, int parentIndex,
			Genome[] offspring, int offspringIndex) {
		EncogProgram program = (EncogProgram)parents[parentIndex];
		EncogProgram result = (EncogProgram)offspring[offspringIndex];
		result.clear();
		
		// find the mutation point, this is simply a node position based on the
		// node count, it does not take int account node-sizes. Also, because this
		// is RPN, the mutation point is the end of the mutation.
		int programSize = program.size();
		int mutationPosition = rnd.nextInt(programSize);
		
		// now find the actual frame index of the end of the mutation
		int mutationIndex = program.findFrame(mutationPosition);
		
		int mutationStart = program.findNodeStart(mutationIndex);
		int mutationSize = (program.nextIndex(mutationIndex) - mutationStart);
		int mutationEnd = mutationStart+mutationSize;
		
		// copy left of the mutation point
		result.copy(program, 0, 0, mutationStart);
		result.setProgramLength(mutationStart);
		result.setProgramCounter(mutationStart);
		
		// handle mutation point
		this.rnd.createNode(rnd, result, 0);
		
		// copy right of the mutation point
		int rightSize = program.getProgramLength()-mutationStart-mutationSize;
		int t = result.getProgramLength();
		result.setProgramLength(result.getProgramLength()+rightSize);
		result.copy(program, mutationEnd, t, rightSize);			
		
		result.size();
		
	}

	@Override
	public int offspringProduced() {
		return 1;
	}

	@Override
	public int parentsNeeded() {
		return 2;
	}
}
