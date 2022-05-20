import { renderTemplate, setActive, showPage } from "./utils.js"
import { setupCandidatesHandlers, loadCandidates } from "./js-for-pages/candidates.js"
import { setupCandidateAddHandlers } from "./js-for-pages/candidate-add.js"

function renderMenuItems(evt) {
  const element = evt.target
  setActive(element)
  const id = element.id;
  renderTemplate(id)  //This setups the HTML for the page
  switch (id) {
    //Here you can execute JavaScript for the selected page
    case "page-candidates": {
      setupCandidatesHandlers()
      loadCandidates()
      break
    }
    case "page-add-candidates": {
      setupCandidateAddHandlers()
      break
    }
  }
}

document.getElementById("menu").onclick = renderMenuItems;
showPage("page-about") //Set the default page to render




