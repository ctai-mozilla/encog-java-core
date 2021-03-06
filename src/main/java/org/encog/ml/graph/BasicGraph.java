/*
 * Encog(tm) Core v3.2 - Java Version
 * http://www.heatonresearch.com/encog/
 * http://code.google.com/p/encog-java/
 
 * Copyright 2008-2012 Heaton Research, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *   
 * For more information on Heaton Research copyrights, licenses 
 * and trademarks visit:
 * http://www.heatonresearch.com/copyright
 */
package org.encog.ml.graph;

import java.util.ArrayList;
import java.util.List;

public class BasicGraph {
	
	private final List<BasicNode> nodes = new ArrayList<BasicNode>();
	private final BasicNode root;
	
	public BasicGraph(BasicNode rootNode) {
		this.root = rootNode;
		nodes.add(rootNode);
	}

	public List<BasicNode> getNodes() {
		return this.nodes;
	}

	/**
	 * @return the root
	 */
	public BasicNode getRoot() {
		return root;
	}

	public BasicNode connect(BasicNode baseNode, BasicNode newNode, double cost) {
		this.nodes.add(newNode);
		baseNode.connect(newNode,cost);
		return newNode;
	}
	
	
	
}
