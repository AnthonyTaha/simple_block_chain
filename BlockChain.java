import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class BlockChain {
	public static ArrayList<Block> dataChain = new ArrayList<Block>();
	
	public static void main(String[] args){
		dataChain.add(new Block("Init","0"));
		dataChain.add(new Block("Second DataBlock",dataChain.get(dataChain.size() -1 ).hash));
		dataChain.add(new Block("Third DataBlock",dataChain.get(dataChain.size() -1 ).hash));
		
		String dataChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(dataChain);
		System.out.println("json:\n"+dataChainJson);
	}
	
	public boolean isChainValid(){
		Block previousBlock;
		Block currentBlock;
		
		for(int i = 1;i < dataChain.size();i++){
			currentBlock = dataChain.get(i);
			previousBlock = dataChain.get(i-1);
			
			if(!currentBlock.hash.equals(currentBlock.calculateHash())){
				System.out.println("Block("+i+"):Current hashes not equal!");
				return false;
			}
			
			if(!previousBlock.hash.equals(currentBlock.previousHash)){
				System.out.println("Block("+i+"):Previous hashes not equal!");
				return false;
			}
		}
		
		return true;
	}
}
