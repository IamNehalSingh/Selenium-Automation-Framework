package utils;

import java.util.ArrayList;
import java.util.List;

public class TestResultManager {

	public static class TestResult {
		public String testName;
		public boolean isPassed;

		public TestResult(String testName, boolean isPassed) {
			this.testName = testName;
			this.isPassed = isPassed;
		}
	}

	private static final List<TestResult> results = new ArrayList<>();

	public static void addResult(String testName, boolean isPassed) {
		results.add(new TestResult(testName, isPassed));
	}

	public static List<TestResult> getResults() {
		return results;
	}

	public static String getHtmlResultTable() {

	    StringBuilder table = new StringBuilder();

	    table.append("<table border='1' cellpadding='10' cellspacing='0' ")
	         .append("style='border-collapse:collapse; font-family:Arial; width:70%;'>");

	    table.append("<tr style='background-color:#2c3e50; color:white;'>")
	         .append("<th>S.No</th>")
	         .append("<th>Test Case</th>")
	         .append("<th>Status</th>")
	         .append("</tr>");

	    int index = 1;

	    for (TestResult result : results) {

	        boolean isEven = index % 2 == 0;
	        String rowBg = isEven ? "#f2f2f2" : "#ffffff";

	        String statusText = result.isPassed ? "✔ PASS" : "✖ FAIL";
	        String statusColor = result.isPassed ? "#1b5e20" : "#b71c1c";

	        table.append("<tr style='background-color:").append(rowBg).append(";'>");

	        table.append("<td>").append(index).append("</td>");
	        table.append("<td>").append(result.testName).append("</td>");
	        table.append("<td style='font-weight:bold; color:")
	             .append(statusColor)
	             .append(";'>")
	             .append(statusText)
	             .append("</td>");

	        table.append("</tr>");

	        index++;
	    }

	    table.append("</table>");
	    return table.toString();
	}
}
