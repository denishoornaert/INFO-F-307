/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.view.TermsAndConditionsPopUp;

/**
 *
 * @author hoornaert
 */
public class TermsAndConditionsPopUpController {

    TermsAndConditionsPopUp _popUp;
    
    public TermsAndConditionsPopUpController() {
        _popUp = new TermsAndConditionsPopUp(this);
        _popUp.setText(getText());
    }

    private String getText() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec elementum, nibh sed tincidunt finibus, diam odio hendrerit erat, sed vehicula quam enim at lacus. Suspendisse euismod ultrices eros at elementum. Integer sodales urna ante, non ultrices mi scelerisque at. Mauris volutpat magna at justo maximus commodo. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin eu nibh a justo lobortis dapibus. Vestibulum sollicitudin sem vel velit semper vulputate sed et massa. Vivamus blandit nunc nec nisi tempor vestibulum. Donec consectetur ornare augue, nec sodales neque tincidunt at. Curabitur a pharetra dolor, non eleifend sapien. Nam egestas luctus odio, sit amet porta enim elementum non. Aliquam non fermentum nibh. Aliquam efficitur massa non urna eleifend congue. Nulla hendrerit lacus non cursus efficitur. Aliquam vitae tellus felis. Aliquam vel felis nibh.\n" +
"\n" +
"Nullam molestie pharetra nisi non dignissim. Suspendisse potenti. Interdum et malesuada fames ac ante ipsum primis in faucibus. Mauris quam nunc, pulvinar sit amet tristique in, ultricies et mi. Donec nec convallis eros. Aliquam placerat, tellus sit amet elementum tristique, enim risus commodo ante, et faucibus nisl neque at ex. Mauris finibus sagittis consequat. In a mollis lectus.\n" +
"\n" +
"Praesent mattis magna lacus, eu tempor neque aliquet in. Praesent blandit est eu dolor ultrices, eu iaculis arcu suscipit. Nulla sapien nisi, hendrerit ac viverra vitae, volutpat vel est. Curabitur eros orci, suscipit id risus a, vulputate iaculis ipsum. Nunc vehicula eu lacus at consectetur. Sed pellentesque, orci sed congue blandit, nunc nunc finibus neque, eu semper libero mi aliquet diam. Nam non magna leo. Phasellus venenatis enim id lacus gravida placerat. Proin vel pharetra velit, at porttitor ante. Praesent blandit nisl sem, sit amet euismod diam condimentum elementum. Phasellus nibh odio, malesuada at ornare commodo, pretium at est. Mauris tempor lacus sed lacus malesuada consequat. Maecenas euismod ligula eros, quis malesuada leo venenatis a.\n" +
"\n" +
"Suspendisse ornare interdum purus, eu pulvinar magna accumsan eu. Suspendisse dignissim varius cursus. Duis turpis mauris, tempor ac pulvinar ac, ullamcorper sed leo. Ut ac diam luctus, lobortis nibh eu, maximus sapien. Cras bibendum volutpat ipsum. Nam ut sodales erat, eget elementum libero. Pellentesque ullamcorper orci vel mauris sollicitudin, ut blandit nunc finibus.\n" +
"\n" +
"Nulla semper consequat elementum. Donec felis urna, suscipit quis aliquam volutpat, ullamcorper sed velit. Vestibulum non nisl eu metus tempor tincidunt sed id dolor. Nunc bibendum erat non dui euismod, a ultricies odio ullamcorper. Donec quis auctor arcu. Curabitur iaculis fringilla ex in congue. In hac habitasse platea dictumst. Mauris sagittis, est vitae rutrum tempor, erat libero bibendum nibh, quis consectetur velit massa at ante. Vestibulum vestibulum, sem et congue mollis, mauris dui commodo arcu, in fermentum mi nulla ut lacus. Nulla eleifend arcu vitae molestie porta. Etiam eget auctor dui.\n" +
"\n" +
"Proin a semper nunc. In suscipit vel diam sit amet imperdiet. Integer tincidunt nibh ac convallis lacinia. Fusce luctus tortor eget nulla mollis, a pulvinar urna rhoncus. Mauris orci ligula, dictum eget tincidunt nec, congue a nisl. Sed eleifend consequat sodales. Vestibulum eget ligula eu mi iaculis sollicitudin. Etiam ac justo ut massa auctor. ";
    }
    
}
