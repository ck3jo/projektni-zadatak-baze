<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="{{ asset('css/welcome.css'); }}" type="text/css">
        <title>Document</title>
    </head>
    <body>
        <h1 class="header">FakeHLTV.org</h1>
        <p>Aplikacija za evidentiranje profesionalnih CS2 mečeva</p>
        <div>
            <a href="{{ url('/authors/') }}">Pogledajte tabelu sa autorima</a>
            <a href="{{ url('/players/') }}">Pogledajte tabelu sa igračima</a>
            <a href="{{ url('/matches/') }}">Pogledajte tabelu sa mečevima</a>
            <a href="{{ url('/teams/') }}">Pogledajte tabelu sa timovima</a>
            <a href="{{ url('/transfers/') }}">Pogledajte tabelu sa transferima</a>
            <a href="{{ url('/coaches/') }}">Pogledajte tabelu sa trenerima</a>
            <a href="{{ url('/tournaments/') }}">Pogledajte tabelu sa turnirima</a>
            <a href="{{ url('/news/') }}">Pogledajte tabelu sa novostima</a>
        </div>
    </body>
</html>