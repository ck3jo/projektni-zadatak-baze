<?php

use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
});

Route::get('/authors', function () {
    return view('authors');
});

Route::get('/players', function () {
    return view('players');
});

Route::get('/matches', function () {
    return view('matches');
});

Route::get('/teams', function () {
    return view('teams');
});

Route::get('/transfers', function () {
    return view('transfers');
});

Route::get('/coaches', function () {
    return view('coaches');
});

Route::get('tournaments', function () {
    return view('tournaments');
});

Route::get('news', function () {
    return view('news');
});