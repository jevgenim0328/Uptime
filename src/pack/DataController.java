package pack;


public class DataController {
	final static int ROWS_IN_PAGE = 12;
	final static int MAX_ROWS = 131;    // Max amount of rows to possible view.
    // Method to make request to amazon.com and return first page of answers.
	//_________________________________________
	public static String DataRequest(String str){
		Uptime.processingRequest(str);    
		return getLitleList(0);
	}
	// Method receive list to do display on output page. 
  //_______________________________________
	public static String getLitleList(int fromNr){
		String response = "";
		int currentPageNr = 0;
		if(Uptime.goodsList==null) {
			return response;
		}
		Uptime.goodsList.stream().forEach(g->Uptime.spekulanto(g));
		StringBuilder hL = new StringBuilder();
		hL.append("<table id=\"#essa\"><col width=\"80\"><col width=\"500\"><col width=\"80\"><col width=\"80\"><col width=\"80\"><col width=\"80\">\n");
		hL.append("<tr><th>Pilt</th><th>Kauba nimetus</th><th>Hind</th><th>Currency</th><th>Hind</th><th>EUR</th></tr>\n");
		for(int i=fromNr,j=0;i<Uptime.goodsList.size()&&j++<ROWS_IN_PAGE;i++) {
			Uptime.goodsList.stream().forEach(g->Uptime.spekulanto(g));
			hL.append("<tr");
			if(i%2==0) 
				hL.append(" bgcolor=\"#eee\"");
			else
				hL.append(" bgcolor=\"#fff\"");
			hL.append("><td><img src=\"");
			hL.append(Uptime.goodsList.get(i).getPictureLink());
			hL.append("\"></td><td>");
			hL.append(Uptime.goodsList.get(i).getName());
			hL.append("</td><td>");
			hL.append(Uptime.goodsList.get(i).getPrice());
			hL.append("</td><td>");
			hL.append(Uptime.goodsList.get(i).getCurrencyBuy());
			hL.append("</td><td>");
			hL.append(Uptime.goodsList.get(i).getConvertedPrice());
			hL.append("</td><td>");
			hL.append(Uptime.goodsList.get(i).getCurrencyGet());
			hL.append("</td></tr>\n");
		  }
		
		if(fromNr>0) {
		    hL.append("<tr><td><INPUT TYPE=\"SUBMIT\" value=\"Eelmine lehekülg\" id=\"previous\" onclick='getNextPage(");
		    hL.append(fromNr-13);
		    hL.append("\")'>\"</td>");
		  }	else {
			hL.append("<tr><td>Algus</td>");
		  }
		 hL.append("<td>Page ");
		 currentPageNr = (fromNr+13)/13;    // Calculate current page number to display on last row.
		 hL.append(currentPageNr);   
		 hL.append("</td><td></td><td></td>");
		 if(fromNr<MAX_ROWS-13) {
			 hL.append("<td><INPUT TYPE=\"SUBMIT\" value=\"Järgmine lehekülg\" id=\"next\" onclick='getNextPage(");
		     hL.append(fromNr + 13);
		     hL.append(")'></td>");
		 } else {
				hL.append("<tr><td>Lõpp</td>");
		 }
		 hL.append("</table>");
		response = hL.toString();
		return response;
		
	}
	public void refreshRates(int fromNr, int toNr){};
}
