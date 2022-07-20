package com.imc.siemens_aas.i4_0.message.frame;

public interface MessageType {
    String CallForPro = "callForProposal";
    String Proposal = "proposal";
    String AcceptProposal = "acceptProposal";
    String RejectProposal = "rejectProposal";
    String Inform = "inform";
    String NotUnderStood = "notUnderstood";
    String Refusal = "refusal";
    String Offer = "offer";
    String OfferRejection = "offerRejection";
    String OfferAcceptance = "offerAcceptance";
    String Conforming = "conforming";
    String Error = "error";
}
