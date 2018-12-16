var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    var symbol = $("#symbol").val();
    subscribe(symbol);
    stompClient.send("/app/priceRequest", {}, JSON.stringify({'symbol': symbol }));
}

function showPrice(message){
    var rowIdentifier = "#"+message.mdRequestID;
    if ($(rowIdentifier).length){
        $(rowIdentifier + " > .symbol").text(message.symbol);
        $(rowIdentifier + " > .bid").text(message.bid);
        $(rowIdentifier + " > .ask").text(message.ask);
    } else {
        var row = $("<tr>");
        row.attr("id",message.mdRequestID);
        row.append("<td class='symbol'>" + message.symbol + "</td>")
            .append("<td class='bid'>" + message.bid + "</td>")
            .append("<td class='ask'>" + message.ask + "</td>")
        $("#greetings").append(row);
    }
}

function subscribe(symbol){
    stompClient.subscribe('/topic/'+symbol, function (greeting) {
        showPrice(JSON.parse(greeting.body));
    });
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

