<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <!-- fontovi -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300..800;1,300..800&display=swap" rel="stylesheet">
        
        <title>FakeHLTV.org</title>
        @vite('resources/css/app.css')
    </head>
    <body class="pattern-dots pattern-slate-800 pattern-bg-slate-900 pattern-opacity-100 pattern-size-4">  
        <div class="h-screen">
            <livewire:navbar />
            <div class="pt-80">
                <h1 class="py-3 text-white drop-shadow-lg font-bold text-8xl font-sans text-center">FakeHLTV.org</h1>
                <h3 class="pt-3 text-white drop-shadow-lg text-2xl font-sans text-center"> Aplikacija za evidentiranje profesionalnih CS2 mečeva</h3>
                <p class="py-4 text-white drop-shadow-lg text-base font-sans text-center">Željenu tabelu možete izabrati putem navigacionog menija na vrhu ekrana. <br> Napravljeno uz pomoć Laravela, Livewirea i PHP-a. <3</p>
            </div>
        </div>
    </body>
</html>