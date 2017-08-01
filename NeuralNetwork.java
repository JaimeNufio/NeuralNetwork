public class NeuralNetwork{
	//originall I had a variable for each value passed to the constructor, dunno why
	Perceptron[] inSet, outSet, hiddenSet;
	int learningRate;
	//Feed Forward Process
	//-Weighted Sums

	void NeuralNetwork(int in, int out, int hidden, int learningRate){
		inSet = new Perceptron[in];
		outSet = new Perceptron[out];
		hiddenSet = new Perceptron[hidden];
		this.learningRate = learningRate;	
	}

	Matrix guess(double[] inputs){
	
		double[][] weights = this.prepareWeights(this.inSet,inputs.length);

		Matrix inputMatrix = new Matrix(inputs); 	//ex, {1,2,4,5}
		inputMatrix.flip(); 				//now its stacked
		
		Matrix weightMatrix = new Matrix(weights);	//weights {0,0,0,0}
		weightMatrix.flip();				//now stacked
	
		Matrix layerOneMatrix = inputMatrix.dotProduct(weightMatrix); //dot product to get x1, ..xn for input neurons

		double[][] hiddenWeights = this.prepareWeights(this.hiddenSet,this.inSet.length);
		
		Matrix hiddenMatrix = new Matrix(hiddenWeights);
		hiddenMatrix.dotProduct(layerOneMatrix);

		double[][] outputWeights = this.prepareWeights(this.outSet,this.hiddenSet.length);
		
		Matrix outputMatrix = new Matrix(outputWeights);
		outputMatrix.dotProduct(layerOneMatrix);

		return outputMatrix;				//This NEEDS to return a X-by-1 

	}

	void train(double[] inputs, Matrix Expected){
	
		Matrix outputMatrix = this.guess(inputs);
		Matrix errorMatrix = Expected.elementWiseSub(outputMatrix);//We got a X-by-1 error matrix, hopefully

		errorMatrix.flip(); outputMatrix.flip();	
		
		double[] errors = errorMatrix.getArray()[0];
		double[] outs = outputMatrix.getArray()[0];  
		double[][] weights = prepareWeights(this.outSet,this.hiddenSet.length);


		//TODO: create an array the size of the adjustment matrix

		for (int i = 0; i < weights.length; i++){
			double backPropTotal = outs[i]*errors[i]*this.learningRate;
			double sumWeights = totalWeights[i];
			for(int j = 0; j < weights[i].length ; j++){
				double percentWeight = weights[i][j]/totalWeights[i];
				//TODO:set adjustment value at position in adjustment Matrix
			}
		}
		//TODO: add matricies, piecewise 

	}

	private double[][] prepareWeights(Perceptron[] dataSet,int numInputs){	
		double[][] weights = new double[numInputs][this.inSet[0].getWeights().length];
		for (int i = 0; i < numInputs; i++)
		{
			weights[i] = dataSet[i].getWeights();
		}
		return weights;
	}

	private double totalWeight(double[] weights){
		double sum = 0;
		for (int i = 0; i < weights.length; i++){
			sum+=weights[i];
		}
		return sum;
	}
}
