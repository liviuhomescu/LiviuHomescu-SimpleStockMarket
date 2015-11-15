<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Hello JPMorgan</h3>
        <table>
        <tr>
             <td id="dividendYeld">DividendYield</td>
             <td id="volumeWeightedStockPrice">Volume wheighted stock price</td>
        </tr>
        <tr>
            <td id="PERario">P /E ratio</td>
            <td id="geometricMean">Geometric mean</td>
        </tr>
        </table>

    </body>

<script language="javascript">
window.onload = addTests();
function addTestRequest(url, testText, successTest, expectedValue, errorText, resultDiv) {
      var request = new XMLHttpRequest();
      request.onreadystatechange = function()
          {
              if (request.readyState == 4 && request.status == 200)
              {
                  addTextToDocument(testText + " : " +  (request.responseText == expectedValue ? successTest + " :: " + request.responseText : "Expected[" + expectedValue +"] but got [" + request.responseText + "]"), resultDiv);
              }
              if (request.readyState == 4 && request.status != 200) {
                addTextToDocument(testText + " : " +  errorText + " :: "  + "Expected[" + expectedValue +"] but got [" + request.responseText + "]", resultDiv);

              }
          };
      request.open("GET", url, true);
      request.send();
}
function addRequest(url) {
      var request = new XMLHttpRequest();
      request.open("GET", url, true);
      request.send();
}

function addTextToDocument(text, resultDiv){
        var para = document.createElement("p");
        var textNode = document.createTextNode(text);
        para.appendChild(textNode);
        document.getElementById(resultDiv).appendChild(para);
        textNode.innerHTML=text;

    }
function addTests(){
    addTestRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/getDividend.htm/stock=POP&price=2","Dividend yield Test for stock=POP and price=2" ,"OK", "4.0", "We got an error", "dividendYeld");
    addTestRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/getDividend.htm/stock=TEA&price=12","Dividend yield Test for stock=TEA and price=12" ,"OK", "0.0", "We got an error", "dividendYeld");
    addTestRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/getDividend.htm/stock=ALE&price=51","Dividend yield Test for stock=ALE and price=51" ,"OK", "0.45098039215686275", "We got an error", "dividendYeld");
    addTestRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/getDividend.htm/stock=GIN&price=143","Dividend yield Test for stock=GIN and price=143" ,"OK", "0.013986013986013986", "We got an error", "dividendYeld");
    addTestRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/getDividend.htm/stock=JOE&price=75","Dividend yield Test for stock=JOE and price=75" ,"OK", "0.17333333333333334", "We got an error", "dividendYeld");

    addTestRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/getPERatio.htm/stock=POP&price=2","P/E Ratio Test for stock=POP and price=2" ,"OK", "0.25", "We got an error", "PERario");
    addTestRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/getPERatio.htm/stock=TEA&price=12","P/E Ratio Test for stock=TEA and price=12" ,"OK", "Infinity", "We got an error", "PERario");
    addTestRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/getPERatio.htm/stock=ALE&price=51","P/E Ratio Test for stock=ALE and price=51" ,"OK", "2.217391304347826", "We got an error", "PERario");
    addTestRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/getPERatio.htm/stock=GIN&price=143","P/E Ratio Test for stock=GIN and price=143" ,"OK", "71.5", "We got an error", "PERario");
    addTestRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/getPERatio.htm/stock=JOE&price=75","P/E Ratio Test for stock=JOE and price=75" ,"OK", "5.769230769230769", "We got an error", "PERario");

    addRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/addTrade.htm/executionTimestamp=03-04-2016:04:30&numberOfShares=10&buyOrSell=sell&tradePrice=3.1");
    addRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/addTrade.htm/executionTimestamp=03-04-2016:04:30&numberOfShares=10&buyOrSell=buy&tradePrice=3.1");
    addRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/addTrade.htm/executionTimestamp=03-04-2016:04:30&numberOfShares=10&buyOrSell=buy&tradePrice=3.1");
    addRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/addTrade.htm/executionTimestamp=03-04-2016:04:30&numberOfShares=20&buyOrSell=sell&tradePrice=6.2");

    setTimeout( function(){
            addTestRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/calculateGeometricMeanOfAllIndices.htm","Geometric mean for sell 10@3.1, buy 10@3.1, buy 10@3.1, buy 20@6.2 " ,"OK", "3.6865420565084355", "We got an error", "geometricMean");
            addTestRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/volumeWeightedStockPrice.htm","VolumeWeightedStockPrice for sell 10@3.1, buy 10@3.1, buy 10@3.1, buy 20@6.2 " ,"OK", "4.34", "We got an error", "volumeWeightedStockPrice");
            }, 5000);

}

function addStocks(numberOfStocks){
    for (i = 0; i< numberOfStocks; i++){
         addRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/addStock.htm/stock=TEA&type=Common&lastDividend=0&parValue=100");
         addRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/addStock.htm/stock=POP&type=Common&lastDividend=8&parValue=100");
         addRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/addStock.htm/stock=ALE&type=Common&lastDividend=23&parValue=60");
         addRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/addStock.htm/stock=GIN&type=Preferred&lastDividend=8&fixedDividend=2.0&parValue=100");
         addRequest("http://localhost:8080/LiviuHomescu-SimpleStockMarket/addStock.htm/stock=JOE&type=Common&lastDividend=13&parValue=250");
    }
}

</script>


</html>