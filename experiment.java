public class experiment{


	public static void main(String[] args){

		//Using y = x
		//1 if y > x	
		double[][] input = {{2,3},{0,5},{3,5},{4,1},{20,12},{5,2},{12,5},{2.5,6},{7,12}};
		int[] out = {1,1,1,0,0,0,0,1,1};

		Perceptron p = new Perceptron(input,out,.2);
		
		int reps = 1;	

		for (int k = 0; k < reps; k++){
			for (int i = 0; i < input.length; i++){
				p.train(input[i],out[i]);
			}
		}

		System.out.println("Guess: "+p.guess(input[1]));
		System.out.println("Guess: "+p.guess(input[3]));

		p.printWeights();

		double[] testData = {-5,-7.1};	
		double[] testDataOne = {10,11};
		System.out.println("Should Read 0: "+Math.round(p.guess(testData)));	
		System.out.println("Should Read 1: "+Math.round(p.guess(testDataOne)));


		Matrix test = new Matrix(out);
		System.out.println(test);
		test.flip();
		System.out.println("\n"+test);

		
/*
		double[][] test = {{1,2,3},{4,5,6},{7,8,9}};

		Matrix m = new Matrix(test);
		System.out.println(m.toString());
		double[] newArray = m.getRow(0);	
		for (int i = 0; i<newArray.length; i++){
			System.out.println(newArray[i]);
		}


		double[][] aone = {{1,1,-1},{4,0,2},{1,0,0}}, atwo = {{2,-1},{3,-2},{0,1}};
		Matrix one = new Matrix(atwo), two = new Matrix(aone);
		
		System.out.println("\nOne:\n"+one);
		System.out.println("Two:\n"+two);

		one.dotProduct(two);
		System.out.println(one+"\n\n"+two);
		
		double[][] weights = {{1,2},{2,4}};
		double[][] inputs = {{5},{4}};
		Matrix weightP = new Matrix(weights);
		Matrix inputP = new Matrix(inputs);
		Matrix result = weightP.dotProduct(inputP);

		System.out.println(weightP+"\n"+inputP+"\n"+result);	
		*/

	}
}
