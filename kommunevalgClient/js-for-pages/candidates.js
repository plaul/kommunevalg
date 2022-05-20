
import { CANDIDATES_URL } from "./settings.js"
import { encode } from "../utils.js"
import { handleHttpErrors } from "../fetchutils.js"

export function setupCandidatesHandlers() {
  // document.getElementById("load-btn").onclick = loadCandidates;
  document.getElementById("party-select").onclick = loadCandidates;
}

export async function loadCandidates(evt) {

  evt?.preventDefault()
  const letter = document.getElementById("party-select").value
  let queryParam = ""
  if (letter != "ALL") {
    queryParam = "?party=" + letter
  }
  const candidates = await fetch(CANDIDATES_URL + queryParam).then(res => handleHttpErrors(res))

  const rows = candidates.map(c => `
      <tr>
        <td>${encode(c.id)}</td> 
        <td>${encode(c.partyLetter)}</td> 
        <td>${encode(c.name)}</td>
        <td>${encode(c.commune)}</td>
      </tr>
    `).join("")
  document.getElementById("tbody").innerHTML = rows


}