
import { CANDIDATES_URL } from "./settings.js"
import { handleHttpErrors, makeOptions } from "../fetchutils.js"

export function setupCandidateAddHandlers() {
  document.getElementById("btn-find-candidate").onclick = findCandidate
  document.getElementById("btn-clear").onclick = clear
  document.getElementById("btn-submit").onclick = submit
}

function clear() {
  document.getElementById("given-id").value = ""
  document.getElementById("input-name").value = ""
  document.getElementById("input-commune").value = ""
  document.getElementById("input-party-letter").value = ""
}

async function submit(evt) {
  evt.preventDefault()
  const candidate = {}
  candidate.id = document.getElementById("given-id").value
  candidate.name = document.getElementById("input-name").value
  candidate.commune = document.getElementById("input-commune").value
  candidate.partyLetter = document.getElementById("input-party-letter").value

  const method = candidate.id === "" ? "POST" : "PUT";

  const options = makeOptions(method, candidate);
  const url = method === "PUT" ? CANDIDATES_URL + "/" + candidate.id : CANDIDATES_URL
  try {
    document.getElementById("error").innerText = ""
    const c = await fetch(url, options).then(handleHttpErrors)
    document.getElementById("given-id").value = c.id
    document.getElementById("input-name").value = c.name
    document.getElementById("input-commune").value = c.commune
    document.getElementById("input-party-letter").value = c.partyLetter

  } catch (ex) {
    document.getElementById("error").innerText = ex.message

  }

}

async function findCandidate(evt) {
  evt.preventDefault();
  const id = document.getElementById("input-id").value

  try {
    document.getElementById("error").innerText = ""
    const candidate = await fetch(`${CANDIDATES_URL}/${id}`).then(handleHttpErrors)
    document.getElementById("given-id").value = candidate.id
    document.getElementById("input-name").value = candidate.name
    document.getElementById("input-commune").value = candidate.commune
    document.getElementById("input-party-letter").value = candidate.partyLetter
  } catch (ex) {
    document.getElementById("error").innerText = ex.message
  }


}