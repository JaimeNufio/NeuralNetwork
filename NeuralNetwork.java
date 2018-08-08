public class NeuralNetwork{
	
	Perceptron[] inSet, hiddenSet;
	int out;
	double learningRate;
	//Feed Forward Process
	//-Weighted Sums

	//middle layer should have nodes in range between in and out

	public NeuralNetwork(int in, int hidden, int out, double learningRate){
		
		//inputs -> # Weights for first layer
		//in -> # Nodes first layer
		//inputsMid -> Implied to be equal to in?


		this.learningRate = learningRate; //I guess we'll keeps tabs on the learning rate?	
		this.inSet = new Perceptron[in];
		this.hiddenSet= new Perceptron[hidden];
//		this.outSet = new Perceptron[out];
		for (int i = 0; i<in;i++){
			inSet[i] = new Perceptron(hidden,learningRate);
		}
		for (int i = 0; i<hidden;i++){
			hiddenSet[i] = new Perceptron(out,learningRate);
		}
		this.out = out;	
	}

	Matrix guess(double[] inputSet){
		if (inputSet.length == inSet.length){		//inputSet is a column of values,in Set is the number of Nodes in the first layer
			Matrix in = new Matrix(inputSet);		//Use single array, column constructor		
//			System.out.println(in);
			double[][] skeleton = new double[hiddenSet.length][inputSet.length];	//creatue matrix that is X: inputs/first layer Nodes Y: Hidden Layer Nodes
			Matrix inWeights = new Matrix(skeleton);
			for (int i = 0; i<inSet.length; i++){
				inWeights.writeToColumn(i,inSet[i].getWeights()); //fill with weights, they are read top-bottom per node
			}
//			System.out.println(inWeights);
			Matrix toHidden = in.dotProduct(inWeights);
//			System.out.println(toHidden);
			toHidden.sigmoid();	
//			System.out.println(toHidden);
			//Thru the first layer
			double[][] skeleHidden = new double[out][hiddenSet.length];	
			Matrix hiddenWeights = new Matrix(skeleHidden);
			for (int i = 0; i<hiddenSet.length; i++){
				hiddenWeights.writeToColumn(i,hiddenSet[i].getWeights()); //fill with weights, they are read top-bottom per node
			}
//			System.out.println(hiddenWeights);
			Matrix output = toHidden.dotProduct(hiddenWeights);	
			output.sigmoid();
			System.out.println(output);
			return output;
		}
		System.out.println("Bad data set.");
		return null;
	}

}
