public class NeuralNetwork{
	//originall I had a variable for each value passed to the constructor, dunno why
	Perceptron[] inSet, outSet, hiddenSet;
	int learningRate;
	//Feed Forward Process
	//-Weighted Sums

	void NeuralNetwork(int in, int out, int hidden, int learningRate){
		
		this.learningRate = learningRate; //I guess we'll keeps tabs on the learning rate?	
		this.inSet = new Perceptron[in];
		this.initPerceptron(this.inSet,hidden);
		this.outSet = new Perceptron[out];
		this.initPerceptron(this.outSet,out);
		this.hiddenSet = new Perceptron[hidden];
		this.initPerceptron(this.hiddenSet,1);
	
	}

	void initPerceptron(Perceptron[] layer, int numWeights){
		
		for (int i = 0; i<layer.length; i++){
			layer[i] = new Perceptron(numWeights,this.learningRate);
		}
	}
	
	Matrix guess(double[] inputs){
	
		Matrix theMatrix = new Matrix(inputs); //Input
		theMatrix = theMatrix.dotProduct(prepWeights(this.inSet));
		theMatrix = theMatrix.dotProduct(prepWeights(this.hiddenSet));
		theMatrix = theMatrix.dotProduct(prepWeights(this.outSet));

		return theMatrix; //Neo, or something 

	}

	void train(double[] inputs, double[] expected){
		
		Matrix inputSet = this.guess(inputs);
		Matrix expectedSet = new Matrix(expected);
		Matrix ErrorSet = inputSet.elementWiseSub(expectedSet);
		//Out Layer can be skipped
		
		
		//Hidden Layer *******************************************************************************************
		double[][] hiddenWeights = new double[this.hiddenSet.length][this.hiddenSet[0].getWeights().length];
		for (int i = 0; i < hiddenWeights.length; i++){
			hiddenWeights[i] = this.hiddenSet[i].getWeights();
		}

		hiddenWeights = this.flipArray(hiddenWeights);	//Flip.

		double[][] tempErrorMaster = hiddenWeights; //remember the errors for use later on
		for (int i = 0; i < hiddenWeights[0].length; i++){ //# of weights should match number of outputs
			double totalWeights = totalWeight(hiddenWeights[i]);
		 	double[][] mathSet = adjustLayer(hiddenWeights[i],ErrorSet.toArray()[i][0]);
			hiddenWeights[i] = mathSet[0];
			tempErrorMaster[i] = mathSet[1];
		}

		hiddenWeights = this.flipArray(hiddenWeights);
		tempErrorMaster = this.flipArray(tempErrorMaster);


		for (int i = 0; i < this.hiddenSet.length; i++){
			this.hiddenSet[i].setWeights(hiddenWeights[i]); 
		}
		
		double[] sumErrorSimple = new double[tempErrorMaster.length];
		
		for (int i = 0; i < hiddenSet.length; i++){
			sumErrorSimple[i] = this.totalWeight(tempErrorMaster[i]);
			hiddenSet[i].sumAttachedError = this.totalWeight(tempErrorMaster[i]);
		}		
		//WOOHOO This should have back propogated from output up to hidden layer, sucessfully
	
		//Input Layer *********************************************************************************************
	
		double[][] inputWeights = new double[this.inSet.length][this.inSet[0].getWeights().length];
		for (int i = 0; i < hiddenWeights.length; i++){
			inputWeights[i] = this.hiddenSet[i].getWeights();
		}
		
		inputWeights = this.flipArray(inputWeights);
		
		for (int i = 0; i < inputWeights[0].length; i++){ //# of weights should match number of outputs
			double totalWeights = totalWeight(inputWeights[i]);
		 	double[][] mathSet = adjustLayer(inputWeights[i],ErrorSet.toArray()[i][0]);
			inputWeights[i] = mathSet[0];
			tempErrorMaster[i] = mathSet[1];
		}

		inputWeights = this.flipArray(inputWeights); //at this mark, I have to admit I'm falling asleep a bit.
		tempErrorMaster = this.flipArray(tempErrorMaster);
		
		for (int i = 0; i < this.inSet.length; i++){
			this.inSet[i].setWeights(hiddenWeights[i]);
		}

		/*
		sumErrorSimple = new double[tempErrorMaster.length];
		for (int i = 0; i < this.inSet.length; i++){
			sumErrorSimple[i] = this.totalWeight(tempErrorMaster[i]);
			inSet[i].sumAttachedError = this.totalWeight(tempErrorMaster[i]);
		}
		*/

		//So.... Propogated up to Inputs.

	}

	private double[][] adjustLayer(double[] weights, double error){
		double[] tempArray = weights;
		double[] tempError = tempArray;
		double totalWeight = totalWeight(weights);
		for(int i = 0 ; i<weights.length; i++){
			tempError[i] = error*(weights[i]/totalWeight)*learningRate;
			tempArray[i]+= tempError[i];
		}
		double[][] out =  {tempArray,tempError};
		return out;
	}

	private Matrix prepWeights(Perceptron[] dataSet){ //Get a matrix of weights
		double[][] weights = new double[dataSet.length][dataSet[0].getWeights().length];
		for (int i = 0; i < dataSet.length; i++){
			weights[i] = dataSet[i].getWeights();
		}

		Matrix weightMatrix = new Matrix(weights);
		return weightMatrix.getFlip();
	}

	private double totalWeight(double[] weights){
		double sum = 0;
		for (int i = 0; i < weights.length; i++){
			sum+=weights[i];
		}
		return sum;
	}

	private double[][] flipArray(double[][] oldArray){

		double[][] tempArray = oldArray;
		for (int i = 0; i < tempArray.length; i++){
			for (int k = 0; k < tempArray[0].length; k++){
				tempArray[i][k] = oldArray[k][i];
			}
		}
		return tempArray;

	}
}
