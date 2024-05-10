<div class="block mx-10">
    <div class="rounded-xl flex flex-row flex-wrap mt-8 py-6 mx-auto w-3/4 gap-x-10 gap-y-5 text-white bg-gray-800 justify-center items-center">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
            <path fill-rule="evenodd" d="M10.5 3.75a6.75 6.75 0 1 0 0 13.5 6.75 6.75 0 0 0 0-13.5ZM2.25 10.5a8.25 8.25 0 1 1 14.59 5.28l4.69 4.69a.75.75 0 1 1-1.06 1.06l-4.69-4.69A8.25 8.25 0 0 1 2.25 10.5Z" clip-rule="evenodd" />
        </svg> 

        <div class="flex flex-row gap-x-4 items-center">
            <label for="playerSearch">Nadimak igrača</label>
            <select wire:model.live="playerSearch" class="rounded-full px-2 py-1 bg-gray-700" id="oldTeamSelect">
                @foreach ($teams as $team)
                    <optgroup label="{{ $team->ImeTima }}">
                        @foreach ($team->players as $player)
                            <option value="{{ $player->Nadimak }}">{{ $player->Nadimak }}</option>
                        @endforeach
                    </optgroup>
                @endforeach
                <option selected value="">Izaberi igrača</option>
            </select>
        </div>
        <div class="flex flex-row gap-x-4 items-center">
            <label for="oldTeamSelect">Tim iz kojeg je izašao igrač</label>
            <select wire:model.live="oldTeamSearch" class="rounded-full px-2 py-1 bg-gray-700" id="oldTeamSelect">
                @foreach ($teams as $team)
                    <option value="{{ $team->ImeTima }}">{{ $team->ImeTima }}</option>
                @endforeach
                <option selected value="">Izaberi tim</option>
            </select>
        </div>
        <div class="flex flex-row gap-x-4 items-center">
            <label for="newTeamSelect">Tim u koji je došao igrač</label>
            <select wire:model.live="newTeamSearch" class="rounded-full px-2 py-1 bg-gray-700" id="newTeamSelect">
                @foreach ($teams as $team)
                    <option value="{{ $team->ImeTima }}">{{ $team->ImeTima }}</option>
                @endforeach
                <option selected value="">Izaberi tim</option>
            </select>
        </div>
        <div class="flex flex-row gap-x-4 items-center">
            <p>Granice za datum transfera</p>
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
        <table class="px-5 pt-10 w-3/4 rounded-t-lg">
            <thead class="bg-gray-800">
                <th class="rounded-tl-xl py-3 font-bold text-white">Ime igrača</th>
                <th class="py-3 font-bold text-white">Ime starog tima</th>
                <th class="py-3 font-bold text-white">Ime novog tima</th>
                <th class="rounded-tr-xl font-bold text-white">Datum transfera</th>
            </thead>
            <tbody class="[&>*:nth-child(odd)]:bg-gray-700 [&>*:nth-child(even)]:bg-gray-600">
                @foreach ($transfers as $transfer)
                    <tr class="text-white text-center">
                        <td class="py-2">{{ $transfer->getPlayerName() }}</td>
                        <td class="py-2">{{ $transfer->getOldTeamName() }}</td>
                        <td class="py-2">{{ $transfer->getNewTeamName() }}</td>
                        <td class="py-2">{{ $transfer->getFormattedDate() }}</td>
                    </tr>
                @endforeach
            </tbody>
        </table>
    </div>
</div>
