import { renderTemplate, setActive, showPage } from "./utils.js"
import {setupCandidatesHandlers} from "./js-for-pages/candidates.js"

function renderMenuItems(evt) {
  const element = evt.target
  setActive(element)
  const id = element.id;
  renderTemplate(id)  //This setups the HTML for the page
  switch (id) {
    //Here you can execute JavaScript for the selected page
    case "page-candidates": {
        setupCandidatesHandlers()
      break
    }
  }
}

document.getElementById("menu").onclick = renderMenuItems;
showPage("page-about") //Set the default page to render




