
import org.apache.commons.math3.stat.inference.TTest;
import org.jfree.data.time.TimePeriodValues;

public class StatsMod {
	public double timeSeriesTTest(TimePeriodValues ser1,TimePeriodValues ser2) {
		
		TTest tt = new TTest();
		
		double[] arr1 = toValArr(ser1);
		
		double[] arr2 = toValArr(ser2);
		
		return tt.tTest(arr1, arr2);
	}
	private double[] toValArr(TimePeriodValues tpv) { //converts timeperiodvalues to array of doubles which contains the values ordered from first added to last added
		int j = tpv.getItemCount();
		double[] ret = new double[j];
		for(int i = 0; i<j;i++) {
			ret[i]=tpv.getValue(i).doubleValue();
		}
		return ret;
	}
}
