import { Moment } from 'moment';
import { IPm } from 'app/shared/model/pm.model';
import { IContrat } from 'app/shared/model/contrat.model';
import { IPlanprevetinf } from 'app/shared/model/planprevetinf.model';
import { IEquipement } from 'app/shared/model/equipement.model';
import { IDemandeintervention } from 'app/shared/model/demandeintervention.model';

export interface IEquipement {
    id?: number;
    nomEquipement?: string;
    marque?: string;
    description?: string;
    dateAchat?: Moment;
    datedernieremaintenance?: Moment;
    dateexperation?: Moment;
    fabricant?: string;
    prixAchat?: number;
    numeroSerie?: number;
    numeroCommande?: number;
    imageContentType?: string;
    image?: any;
    pms?: IPm[];
    contrats?: IContrat[];
    equipavoirplans?: IPlanprevetinf[];
    equipementParentId?: number;
    equipementFils?: IEquipement[];
    equipsdemandes?: IDemandeintervention[];
    typeequipementId?: number;
    localisationsId?: number;
    serviceeId?: number;
}

export class Equipement implements IEquipement {
    constructor(
        public id?: number,
        public nomEquipement?: string,
        public marque?: string,
        public description?: string,
        public dateAchat?: Moment,
        public datedernieremaintenance?: Moment,
        public dateexperation?: Moment,
        public fabricant?: string,
        public prixAchat?: number,
        public numeroSerie?: number,
        public numeroCommande?: number,
        public imageContentType?: string,
        public image?: any,
        public pms?: IPm[],
        public contrats?: IContrat[],
        public equipavoirplans?: IPlanprevetinf[],
        public equipementParentId?: number,
        public equipementFils?: IEquipement[],
        public equipsdemandes?: IDemandeintervention[],
        public typeequipementId?: number,
        public localisationsId?: number,
        public serviceeId?: number
    ) {}
}
