<div class="flex justify-center py-10 rounded-t-lg overflow-hidden">
    <table class="px-5 pt-10 w-5/6 rounded-t-lg">
        <thead class="bg-gray-800">
            <th class="rounded-tl-xl py-3 font-bold text-white">Ime turnira</th>
            <th class="py-3 font-bold text-white">Datum početka</th>
            <th class="py-3 font-bold text-white">Datum završetka</th>
            <th class="py-3 font-bold text-white">Mesto igranja</th>
            <th class="py-3 font-bold text-white">Nagradni fond</th>
            <th class="rounded-tr-xl py-3 font-bold text-white">Veći turnir?</th>
        </thead>
        <tbody class="[&>*:nth-child(odd)]:bg-gray-700 [&>*:nth-child(even)]:bg-gray-600">
            @foreach ($tournaments as $tournament)
                <tr class="text-white text-center">
                    <td class="py-2">{{ $tournament->Ime }}</td>
                    <td class="py-2">{{ $tournament->DatumPocetka }}</td>
                    <td class="py-2">{{ $tournament->DatumZavrsetka }}</td>
                    <td class="py-2">{{ $tournament->MestoIgranja }}</td>
                    <td class="py-2">{{ $tournament->NagradniFond }}</td>
                    <td class="py-2 text-white">{{ $tournament->VeciTurnir === 1 ? "✔" : "❌"}}</td>
                </tr>
            @endforeach
        </tbody>
    </table>
</div>
