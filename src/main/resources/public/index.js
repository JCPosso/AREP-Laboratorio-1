window.onload = function(){
    var HttpClient = function() {
        this.get = function(aUrl, aCallback) {
        var anHttpRequest = new XMLHttpRequest();
        anHttpRequest.onreadystatechange = function() {
            if (anHttpRequest.readyState == 4 && anHttpRequest.status == 200)
                aCallback(anHttpRequest.responseText);
            }
            anHttpRequest.open( "GET", aUrl, true );
            anHttpRequest.send( null );
        }
    }
    var getUrl = function() {
        var res="";
    //https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=fb&apikey=Q1QZFVJQ21K7C6XM"
        var fun = $("#sl-menu-series option:selected").val();
        var  symbol= $("#data-exchange").val();
        res="https://www.alphavantage.co/query?function="+fun+"&symbol="+symbol+"&apikey=Q1QZFVJQ21K7C6XM"
        return res
    }
    var doc = document.getElementById('btn-success');

    doc.onclick = function (){
        var client = new HttpClient();
        client.get(getUrl(), function(response) {
            $('p#flag').text(response);
            let obj = JSON.parse(response);
            obj =obj[$("#sl-menu-series option:selected").text()];//hacer un if por cada opcion
            let text = ''
                for (let x in obj) {
                  var v1= x;
                  text += '<tr><th scope="row">' + x + '</th>';
                  for ( z in  obj[x] ){
                                   text += '<th scope="row">' + obj[x][z] + '</th>';
                  }
                  text+="</tr>"
                }
                text += ""
                console.log(text);
                document.getElementById("demo").innerHTML = text;
        });
    }
}
