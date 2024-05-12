<div class="block mx-10">
    <div class="rounded-xl flex flex-row flex-wrap gap-x-10 gap-y-5 w-4/5 mx-auto mt-8 py-6 px-4 justify-center items-center text-white bg-gray-800">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
            <path fill-rule="evenodd" d="M10.5 3.75a6.75 6.75 0 1 0 0 13.5 6.75 6.75 0 0 0 0-13.5ZM2.25 10.5a8.25 8.25 0 1 1 14.59 5.28l4.69 4.69a.75.75 0 1 1-1.06 1.06l-4.69-4.69A8.25 8.25 0 0 1 2.25 10.5Z" clip-rule="evenodd" />
        </svg> 

        <input wire:model.live.debounce.150ms="nameSearch" class="placeholder:text-center rounded-full px-2 py-1 text-white bg-gray-700" type="text" placeholder="Ime tima">
        <div class="flex flex-row gap-x-4 items-center">
            <p>Granice za rang poziciju</p>
            <div class="flex flex-col gap-y-0">
                <p class="text-xs text-center">
                    @if ($this->minRanking === "1")
                        Nema
                    @else
                        {{ $this->minRanking }}
                    @endif
                </p>
                <input wire:model.live="minRanking" class="bg-fuchsia-950" value="1" min="1" max="29" step="1" type="range">
                <p class="text-xs text-center">Minimum</p>
            </div>
            <div class="flex flex-col gap-y-0">
                <p class="text-xs text-center">
                    @if ($this->maxRanking === "31")
                        Nema
                    @else
                        {{ $this->maxRanking }}
                    @endif
                </p>
                <input wire:model.live="maxRanking" value="31" min="1" max="31" step="1" type="range">
                <p class="text-xs text-center">Maksimum</p>
            </div>
        </div>
        <div class="flex flex-row gap-x-4 items-center">
            <p>Granice za Major trofeje</p>
            <div class="flex flex-col gap-y-0">
                <p class="text-xs text-center">
                    @if ($this->minMajorTrophies === "0")
                        Nema
                    @else
                        {{ $this->minMajorTrophies }}
                    @endif
                </p>
                <input wire:model.live="minMajorTrophies" class="bg-fuchsia-950" value="0" min="0" max="10" step="1" type="range">
                <p class="text-xs text-center">Minimum</p>
            </div>
            <div class="flex flex-col gap-y-0">
                <p class="text-xs text-center">
                    @if ($this->maxMajorTrophies === "10")
                        Nema
                    @else
                        {{ $this->maxMajorTrophies }}
                    @endif
                </p>
                <input wire:model.live="maxMajorTrophies" value="10" min="1" max="10" step="1" type="range">
                <p class="text-xs text-center">Maksimum</p>
            </div>
        </div>
        <select wire:model.live="regionSearch" class="rounded-full py-2 px-2 bg-gray-700">
            <option value="Evropa">Evropa</option>
            <option value="Severna Amerika">Severna Amerika</option>
            <option value="Južna Amerika">Južna Amerika</option>
            <option value="Azija">Azija</option>
            <option value="Okeanija">Okeanija</option>
            <option value="Afrika">Afrika</option>
            <option value="">Region</option>
        </select>
        <button wire:click="resetFilters()" class="rounded-full px-2 py-1 bg-gray-700">Resetuj filtere</button>
    </div>
    <div class="flex justify-center py-2 rounded-t-lg overflow-hidden">
        <table class="px-5 pt-10 w-4/5 rounded-t-lg">
            <thead class="bg-gray-800">
                <th class="rounded-tl-xl py-3 font-bold text-white w-1/4">Ime tima</th>
                <th class="py-3 font-bold text-white w-1/4">Rang pozicija</th>
                <th class="py-3 font-bold text-white w-1/4">Broj Major trofeja</th>
                <th class="rounded-tr-xl py-3 font-bold text-white w-1/4">Region</th>
            </thead>
            <tbody class="[&>*:nth-child(odd)]:bg-gray-700 [&>*:nth-child(even)]:bg-gray-600">
                @foreach ($teams as $team)
                    <tr class="text-white text-center">
                        <td class="py-2">{{ $team->ImeTima }}</td>
                        <td class="py-2">{{ $team->RangPozicija }}</td>
                        <td class="py-2">{{ $team->MajorTrofeji }}</td>
                        <td class="py-2">{{ $team->Region }}</td>
                    </tr>
                @endforeach
            </tbody>
        </table>
    </div>
    
</div>