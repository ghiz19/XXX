import { IInterevntion } from 'app/shared/model/interevntion.model';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';

export interface IEquipe {
    id?: number;
    nomequipe?: string;
    equipeinterventions?: IInterevntion[];
    equipeusers?: IUtilisateur[];
}

export class Equipe implements IEquipe {
    constructor(
        public id?: number,
        public nomequipe?: string,
        public equipeinterventions?: IInterevntion[],
        public equipeusers?: IUtilisateur[]
    ) {}
}
