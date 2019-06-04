import { IHistoriquetache } from 'app/shared/model/historiquetache.model';
import { IDemandeintervention } from 'app/shared/model/demandeintervention.model';

export interface IUtilisateur {
    id?: number;
    nomresponsable?: string;
    prenomresponsable?: string;
    role?: string;
    equipeId?: number;
    usertaches?: IHistoriquetache[];
    userdemandes?: IDemandeintervention[];
}

export class Utilisateur implements IUtilisateur {
    constructor(
        public id?: number,
        public nomresponsable?: string,
        public prenomresponsable?: string,
        public role?: string,
        public equipeId?: number,
        public usertaches?: IHistoriquetache[],
        public userdemandes?: IDemandeintervention[]
    ) {}
}
