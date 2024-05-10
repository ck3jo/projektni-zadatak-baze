<div>
    <div class="rounded-xl flex flex-row flex-wrap justify-center items-center mt-8 py-6 w-5/6 px-5 gap-x-10 gap-y-5 mx-auto text-white bg-gray-800">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
            <path fill-rule="evenodd" d="M10.5 3.75a6.75 6.75 0 1 0 0 13.5 6.75 6.75 0 0 0 0-13.5ZM2.25 10.5a8.25 8.25 0 1 1 14.59 5.28l4.69 4.69a.75.75 0 1 1-1.06 1.06l-4.69-4.69A8.25 8.25 0 0 1 2.25 10.5Z" clip-rule="evenodd" />
        </svg> 

        <select wire:model.live="firstTeamSearch" class="rounded-full py-2 px-2 bg-gray-700" id="firstTeamSelect">
            @foreach ($teams as $team)
                <option value="{{ $team->ImeTima }}">{{ $team->ImeTima }}</option>
            @endforeach
            <option selected value="" class="text-gray-500">Ime prvog tima</option>
        </select>
        <select wire:model.live="secondTeamSearch" class="rounded-full py-2 px-2 bg-gray-700" id="secondTeamSelect"> 
            @foreach ($teams as $team)
                <option value="{{ $team->ImeTima }}">{{ $team->ImeTima }}</option>
            @endforeach
            <option selected value="" class="text-gray-500">Ime drugog tima</option>
        </select>
        <select wire:model.live="tournamentSearch" class="rounded-full py-2 px-2 bg-gray-700" id="tournamentSearch">
            @foreach ($tournaments as $tournament)
                <option value="{{ $tournament->Ime }}">{{ $tournament->Ime }}</option>
            @endforeach
            <option selected value="" class="text-gray-500">Ime turnira</option>
        </select>
        <div class="flex flex-row gap-x-4 items-center">
            <p>Granice za datum meča</p>
            <div class="flex flex-col gap-x-0">
                <input wire:model.blur="lowerDateSearch" class="rounded-full px-2 py-1 bg-gray-700" type="date" id="lowerDateSelect">
                <p class="font-2xs text-center">Donja</p>
            </div>
            <div class="flex flex-col gap-x-0">
                <input wire:model.blur="upperDateSearch" class="rounded-full px-2 py-1 bg-gray-700" type="date" id="lowerDateSelect">
                <p class="font-2xs text-center">Gornja</p>
            </div>
        </div>
    </div>
    <div class="flex justify-center py-2 rounded-t-lg overflow-hidden">
        <table class="px-5 pt-2 w-5/6 rounded-t-lg">
            <thead class="bg-gray-800">
                <th class="rounded-tl-xl py-3 font-bold text-white">Ime prvog tima</th>
                <th class="py-3 font-bold text-white">Ime drugog tima</th>
                <th class="py-3 font-bold text-white">Ime turnira</th>
                <th class="py-3 font-bold text-white">Broj mapa</th>
                <th class="py-3 font-bold text-white">Rezultat</th>
                <th class="rounded-tr-xl py-3 font-bold text-white">Datum meča</th>
            </thead>
            <tbody class="[&>*:nth-child(odd)]:bg-gray-700 [&>*:nth-child(even)]:bg-gray-600">
                @foreach ($matches as $match)
                    <tr class="text-white text-center">
                        <td class="py-2">{{ $match->getFirstTeamName() }}</td>
                        <td class="py-2">{{ $match->getSecondTeamName() }}</td>
                        <td class="py-2">{{ $match->getTournamentName() }}</td>
                        <td class="py-2">{{ $match->BrojMapa }}</td>
                        <td class="py-2">{{ $match->Rezultat }}</td>
                        <td class="py-2">{{ $match->getFormattedDate() }}</td>
                    </tr>
                @endforeach
            </tbody>
        </table>
    </div>
</div>
