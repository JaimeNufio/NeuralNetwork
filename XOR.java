public class XOR{

	public static void main(String[] args){
	
		double[][] inputs = {{0,0},{0,1},{1,0},{1,1}};
		int[] expectedOut = {0,1,1,0};
		
		double lr = 0.2;

		Matrix t = new Matrix(expectedOut);
//		System.out.print(t);

		Matrix ins = new Matrix(inputs);
		System.out.println(ins);
		ins.flip();
		System.out.println(ins);

		//Layer one
		Perceptron one = new Perceptron(inputs.length,lr); 
		Perceptron two = new Perceptron(inputs.length,lr);

		//Hidden Layer?
		Perceptron three = new Perceptron(inputs.length,lr);


	}

}
