public class Perceptron{

	public double sumAttachedError = 0;
	double learningRate;
	double[] weights;
	double bias = 0;

	Perceptron(int numWeights, double learningRate){
		this.learningRate = learningRate;
		this.weights = new double[numWeights];
		this.setWeights();
//		weights already Init to 0
	}

	private void setWeights(){ //randomly assign values for weights
		for (int i = 0; i<weights.length; i++){
			weights[i] = 0.5;// Math.random();			
		}
	}

	public double guess(double[] input){
		//prepInput(input);
		double output = 0;
		for (int i = 0; i < weights.length; i++){
			output+=this.weights[i]*input[i];
		}
		return sigmoid(output+bias);
	}	

	double[] prepInput(double[] in){
		for (int i = 0; i<in.length;i++){
			in[i] = sigmoid(in[i]);
		}
		return in;
	}

	void train(double[] input, int expected){
	//	prepInput(input);
		double error = expected-guess(input);
		
		/*
		System.out.println("Got: "+guess(input));
		System.out.println("Expected: "+expected);
		System.out.println("Error: "+error);
		*/

		for (int i = 0; i < weights.length; i++){
			this.weights[i]+=error*input[i]*learningRate;
		//	System.out.println("Weight Delta: "+((error/(input[i]+.1))*learningRate));
		}
		bias+=error*learningRate;
		//  System.out.println("Bias Delta: "+((error/(1))*learningRate));
	}

	public void setWeights(double[] newWeights){ //for the NeuralNetwork to set weights in swaths 
		for (int i = 0; i<weights.length-1; i++){
			weights[i]=newWeights[i];
		}
	}

	public void getError(double[] newWeights){ //for the NeuralNetwork to set weights in swaths 
		this.weights = newWeights;
	}

	double[] getWeights(){
		return weights;
	}

	void printWeights(){
		for (int i = 0; i < weights.length; i++){
			System.out.println("Weight "+(i)+": "+weights[i]);
		}
	}
	
	void printBias(){
		System.out.println("Bias: "+bias);//+weights[weights.length-1]);	
	}

	private double sigmoid(double num){
		return (double) 1/(1+Math.exp(-num));
	}

}

