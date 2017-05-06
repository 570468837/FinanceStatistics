import java.io.*;  
  
import javax.swing.text.BadLocationException;  
import javax.swing.text.DefaultStyledDocument;  
import javax.swing.text.rtf.*;  
  
public class Test {  
    String text;  
    DefaultStyledDocument dsd;  
    RTFEditorKit rtf;
    
	class FinanceData{
		String CunHuo;
		String ZiChan;
		String Touzi;
		String Yushou;
		String Changqi;
		String Yingshou;
		String StockId;
		
		String ZiChanZongJi;
		String JingLiRun;
		String JingYingHuoDong;
		String ZhuYingYeWuShouRu;
		String GuDingZiChan;
		String JiBenMeiGuShouYi;
		String JingZiChanShouYiLv;
		
		void print(){
			System.out.println(CunHuo+'\t'+ ZiChan + '\t' 
					+ Touzi + '\t' + Yushou + '\t' + Changqi + '\t' 
					+ Yingshou + '\t' + ZiChanZongJi + '\t'
					+ JingLiRun + '\t' + JingYingHuoDong + '\t'
					+ ZhuYingYeWuShouRu + '\t' + GuDingZiChan + '\t' + JiBenMeiGuShouYi + '\t'
					+ JingZiChanShouYiLv + '\t' + "代码：" + StockId );
		}
	}
 
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        Test t=new Test();  
        File rootFile = new File("data_rtf");
        File[] files = rootFile.listFiles();
        for(File f: files){
        	t.readRtf(f);
        }
       
        //t.writeRtf(new File("e:\\out.rtf"));  
    }  
    
    public void readRtf(File in) {  
        rtf=new RTFEditorKit();  
        dsd=new DefaultStyledDocument();  
        FinanceData data = new FinanceData();
        try {  
        	data.StockId = in.getName().split("\\.")[0];
            rtf.read(new FileInputStream(in), dsd, 0);  
            text = new String(dsd.getText(0, dsd.getLength()));  
            processLine(text, data);
            data.print();
            
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (BadLocationException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
  
    }  
    
    public void processLine(String line, FinanceData data){
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
		
		if(line.contains("资产总计")){
			int index = line.indexOf("资产总计");
			index += 5;
			data.ZiChanZongJi = nextNum(line, index);
		}
		
		if(line.contains("四、净利润")){
			int index = line.indexOf("四、净利润");
			index += 6;
			data.JingLiRun = nextNum(line, index);
		}
		
		if(line.contains("经营活动产生的现金流量净额")){
			int index = line.indexOf("经营活动产生的现金流量净额");
			index += 14;
			data.JingYingHuoDong = nextNum(line, index);
		}
		
		if(line.contains("营业收入")){
			int index = line.indexOf("营业收入");
			index += 5;
			data.ZhuYingYeWuShouRu = nextNum(line, index);
		}
		
		if(line.contains("固定资产")){
			int index = line.indexOf("固定资产");
			index += 5;
			data.GuDingZiChan = nextNum(line, index);
		}
		
		if(line.contains("基本每股收益(元)")){
			int index = line.indexOf("基本每股收益(元)");
			index += 10;
			data.JiBenMeiGuShouYi = nextNum(line, index);
		}
		
		if(line.contains("净资产收益率加权平均(%)")){
			int index = line.indexOf("净资产收益率加权平均(%)");
			index += 15;
			data.JingZiChanShouYiLv = nextNum(line, index);
		}
    }
    
    String nextNum(String line, int index){
    	String res = "";
    	index--;
    	while(index < line.length() && (line.charAt(index) == '-' ||
    			 line.charAt(index)  == ',' || line.charAt(index) == '.' ||
    			 Character.isDigit(line.charAt(index))) ){
    		res += line.charAt(index);
    		index++;
    	}
    	return res;
    }
}  