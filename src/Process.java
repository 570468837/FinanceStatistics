import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Process {
	class FinanceData{
		String CunHuo;
		String ZiChan;
		String Touzi;
		String Yushou;
		String Changqi;
		String Yingshou;
		String StockId;
		
		void print(){
			System.out.println(CunHuo+'\t'+ ZiChan + '\t' 
					+ Touzi + '\t' + Yushou + '\t' + Changqi + '\t' + Yingshou + '\t'
					+ "代码：" + StockId);
		}
	}
	
	public static void main(String[] args) throws IOException{
		Process p = new Process();
		File root = new File("data_txt");
		File[] datas = root.listFiles();
		for(int i=0; i<datas.length; i++){
			p.process(datas[i].getPath());
		}
	}

	public void process(String path) throws IOException{
		File file = new File(path);
        BufferedReader reader = null;
        FinanceData data = new FinanceData();
        String fileName = file.getName();
        data.StockId = fileName.split("\\.")[0];
        InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),"GBK");
        try {
            reader = new BufferedReader(read);
            String tempString = null;
            int line = 1;
            
            while ((tempString = reader.readLine()) != null) {
            	oneLine(tempString, data);
                line++;
            }
            
            data.print();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		
	}
	
	void oneLine(String line, FinanceData data){
		if(line.contains("应收账款")){
			int index = line.indexOf("应收账款");
			index += 5;
			data.Yingshou = nextNum(line, index);
		}
		
		if(line.contains("预收款项")){
			int index = line.indexOf("预收款项");
			index += 5;
			data.Yushou = nextNum(line, index);
		}
		
		if(line.contains("存货")){
			int index = line.indexOf("存货");
			index += 3;
			data.CunHuo = nextNum(line, index);
		}
		
		if(line.contains("长期待摊费用")){
			int index = line.indexOf("长期待摊费用");
			index += 7;
			data.Changqi = nextNum(line, index);
		}
		
		if(line.contains("资产减值损失")){
			int index = line.indexOf("资产减值损失");
			index += 7;
			data.ZiChan = nextNum(line, index);
		}
		
		if(line.contains("投资收益")){
			int index = line.indexOf("投资收益");
			index += 5;
			data.Touzi = nextNum(line, index);
		}
	}
	
	String nextNum(String str, int index){
		String res = "";
		while(index < str.length() && str.charAt(index) != '\t' && str.charAt(index) != '\r' 
				&& str.charAt(index) != '\n'){
			res += str.charAt(index);
			index++;
		}
		return res;
	}
}
