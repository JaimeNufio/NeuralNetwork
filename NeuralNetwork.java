public class NeuralNetwork{
	//originall I had a variable for each value passed to the constructor, dunno why
	Perceptron[] inSet, outSet, hiddenSet;
	double learningRate;
	//Feed Forward Process
	//-Weighted Sums

	public NeuralNetwork(int in, int hidden, int out,  double learningRate){
		
		this.learningRate = learningRate; //I guess we'll keeps tabs on the learning rate?	
		this.inSet = new Perceptron[in];
		inSet = initPerceptron(this.inSet,hidden);
		this.hiddenSet = new Perceptron[hidden];
		hiddenSet = initPerceptron(this.hiddenSet,out);
		//this.outSet = new Perceptron[terminal];
		//outSet = initTerminals(this.outSet,terminal);
	}

	Perceptron[] initPerceptron(Perceptron[] layer, int numWeights){
	
		Perceptron[] temp = layer; //clone the layer to assign later

		for (int i = 0; i<layer.length; i++){
			layer[i] = new Perceptron(numWeights,this.learningRate);
		}
		
		return temp;
	}
	
	Perceptron[] initTerminals(Perceptron[] layer, int terminal){
		Perceptron[] temp = layer;
		for (int i = 0; i<layer.length; i++){
			layer[i] = new Perceptron(1,this.learningRate);
		}
		return temp;
	}


	Matrix guess(double[] inputs){ //full guess
	
		Matrix theMatrix = new Matrix(inputs); //Input
//		System.out.print(theMatrix);
//		System.out.print(prepWeights(this.inSet));
		theMatrix = theMatrix.dotProduct(prepWeights(this.inSet).getFlip());
//		System.out.println(theMatrix);
//		System.out.println(prepWeights(this.hiddenSet));
		theMatrix = theMatrix.dotProduct(prepWeights(this.hiddenSet).getFlip());
//		System.out.println(theMatrix);
		//System.out.println(prepWeights(this.outSet));
		//theMatrix = theMatrix.getFlip().dotProduct(prepWeights(this.outSet).getFlip());

		return theMatrix;  

	}

	Matrix guess(double[] inputs, boolean doesntMatter){ //prints the guess Matrix

		Matrix temp = guess(inputs);
		System.out.println(temp);
		return temp;
	}

	private Matrix prepWeights(Perceptron[] dataSet){ //Get a matrix of weights
		
		/*
		 * w11 w12 w13
		 * w21 w22 w23
		 */

		double[][] weights = new double[dataSet.length][dataSet[0].getWeights().length];

		for (int i = 0; i < dataSet.length; i++){
			weights[i] = dataSet[i].getWeights();
			for (int j = 0; j < weights[0].length;j++){
				weights[i][j] = Sigmoid(weights[i][j]);
			}
		}

		return new Matrix(weights);//.getFlip();
	}

	private double totalWeight(double[] weights){
		double sum = 0;
		for (int i = 0; i < weights.length; i++){
			sum+=weights[i];
		}
		return sum;
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

	private double Sigmoid(double n){
		return 1/(1+Math.exp(-1*n));
	}

	private double SigmoidDer(double n){ //n is the sum
		return Sigmoid(double n)*(1-Sigmoid(n));
	}

	Matrix halfGuess(double[] inputs){
                Matrix theMatrix = new Matrix(inputs);
                theMatrix = theMatrix.dotProduct(prepWeights(this.inSet));
                return theMatrix;
        }

       private double updateWeight(double target, double out, double[] relevantWeights){ //Gradient Descent intensifies. ...or rather, it doesn't.
                double sum = totalWeight(relevantWeights);
                return (target-out)*Sigmoid(sum*out)*(1-Sigmoid(sum*out))*out;
        }

	void train(double[] inputs, double[] expected){
		Matrix outSet = guess(inputs);
		Matrix expectedSet = new Matrix (expected);

		double[][] outArray.toArray(), expectedArray = expectedSet.toArray();
		
		double[] set = new double[inputs.length];

		for (int i = 0; i < outSet.toArray().length; i++){
		//	System.out.printf("%s - %s\n",expectedSet.toArray()[i][0],outSet.toArray()[i][0]);
			set[i] = expectedSet.toArray()[i][0]-outSet.toArray()[i][0];
		}

		Matrix errorSet = new Matrix(set);
		Matrix hiddenWeights = prepWeights(this.hiddenSet);
		System.out.println(hiddenWeights);
		Matrix Error = errorSet.dotProduct(hiddenWeights).getFlip();

		double[][] weight = new double[hiddenSet.length][hiddenSet[0].getWeights().length];
		


		for (int i = 0; i < weightCurrent.length; i++){
			weight[i] = hiddenSet[i].getWeights();
			for (int k = 0; k < weightCurrent.length; k++){
				weight[i][j]-=updateWeight(
			}
		}

			
		
	}


}
