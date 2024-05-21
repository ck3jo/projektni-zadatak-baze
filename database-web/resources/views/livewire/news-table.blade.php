<div class="block mx-10">
    <div class="rounded-xl flex flex-row flex-wrap mt-8 mx-auto py-6 w-5/6 gap-x-10 text-white bg-gray-800 justify-center items-center">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
            <path fill-rule="evenodd" d="M10.5 3.75a6.75 6.75 0 1 0 0 13.5 6.75 6.75 0 0 0 0-13.5ZM2.25 10.5a8.25 8.25 0 1 1 14.59 5.28l4.69 4.69a.75.75 0 1 1-1.06 1.06l-4.69-4.69A8.25 8.25 0 0 1 2.25 10.5Z" clip-rule="evenodd" />
        </svg> 

        <input wire:model.live="titleSearch" class="bg-gray-700 rounded-full py-2 px-1 placeholder:text-center" type="text" placeholder="Naslov">
        <div class="flex flex-row gap-x-4 items-center">
            <p>Granice za datum vesti</p>
            <div class="flex flex-col gap-y-0">
                <input wire:model.blur="lowerDateSearch" class="rounded-full px-2 py-1 bg-gray-700" type="date" id="lowerDatePicker">
                <p class="font-2xs text-center">Donja</p>
            </div>
            <div class="flex flex-col gap-y-0">
                <input wire:model.blur="upperDateSearch" class="rounded-full px-2 py-1 bg-gray-700" type="date" id="upperDatePicker">
                <p class="font-2xs text-center">Gornja</p>
            </div>
        </div>

        <div class="flex flex-row gap-x-4 items-center">
            <label for="">Ime autora</label>
            <select wire:model.live="authorSearch" class="rounded-full px-2 py-1 bg-gray-700" id="authorSelect">
                @foreach ($authors as $author)
                    <option value="{{ $author->Nadimak }}">{{ $author->Nadimak }}</option>
                @endforeach
                <option value="">Ime autora</option>
            </select>
        </div>
        <button wire:click="resetFilters()" class="px-2 py-1 rounded-full bg-gray-700">Resetuj filtere</button>
    </div>
    <div class="flex justify-center py-2 rounded-t-lg overflow-hidden">
        <table class="px-5 pt-10 w-5/6 rounded-t-lg table-auto">
            <thead class="bg-gray-800">
                <th wire:click="setSortBy('Naslov')" class="rounded-tl-xl py-3 font-bold text-white">
                    Naslov
                    <button>
                        @if ($sortBy !== "Naslov")
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15 12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9" />
                            </svg>
                        @elseif ($sortDir === "ASC")
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m4.5 15.75 7.5-7.5 7.5 7.5" />
                            </svg>
                        @else
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m19.5 8.25-7.5 7.5-7.5-7.5" />
                            </svg>
                        @endif
                    </button>   
                </th>
                <th wire:click="setSortBy('DatumObjavljivanja')" class="py-3 font-bold text-white">
                    Datum objavljivanja
                    <button>
                        @if ($sortBy !== "DatumObjavljivanja")
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15 12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9" />
                            </svg>
                        @elseif ($sortDir === "ASC")
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m4.5 15.75 7.5-7.5 7.5 7.5" />
                            </svg>
                        @else
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m19.5 8.25-7.5 7.5-7.5-7.5" />
                            </svg>
                        @endif
                    </button>   
                </th>
                <th class="rounded-tr-xl py-3 font-bold text-white">Ime autora</th>
            </thead>
            <tbody class="table-auto [&>*:nth-child(odd)]:bg-gray-700 [&>*:nth-child(even)]:bg-gray-600">
                @foreach ($newsarr as $news)
                    <tr class="text-white text-center">
                        <td class="py-2">{{ $news->Naslov }}</td>
                        <td class="py-2">{{ $news->getFormattedDate() }}</td>
                        <td class="py-2">{{ $news->author->Nadimak }}</td>
                    </tr>
                @endforeach
            </tbody>
        </table>
    </div>
</div>
