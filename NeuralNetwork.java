public class NeuralNetwork{
	
	Perceptron[] inSet, outSet, hiddenSet;
	double learningRate;
	//Feed Forward Process
	//-Weighted Sums

	//middle layer should have nodes in range between in and out

	public NeuralNetwork(int inputs, int in, int hidden, int out, double learningRate){
		
		//inputs -> # Weights for first layer
		//in -> # Nodes first layer
		//inputsMid -> Implied to be equal to in?


		this.learningRate = learningRate; //I guess we'll keeps tabs on the learning rate?	
		this.inSet = new Perceptron[in];
		for (int i = 0; i<in;i++){
			inSet[i] = new Perceptron(in,learningRate);
		}
	}

	public void guess(double[] inputSet){
		if (inputSet.length == inSet.length){		//inputSet is a column of values,in Set is the number of Nodes in the first layer
			Matrix in = new Matrix(inputSet);		//Use single array, column constructor		
			double[][] skeleton = new double[hiddenSet.length][inputSet.length];	//creatue matrix that is X: inputs/first layer Nodes Y: Hidden Layer Nodes
			Matrix inWeights = new Matrix(skeleton);
			for (int i = 0; i<inSet.length; i++){
				inWeights.writeToColumn(i,inSet[i].getWeights()); //fill with weights, they are read top-bottom per node
			}
			Matrix toHidden = in.dotProduct(inWeights);
		
			//Thru the first layer

		}
	}

}
