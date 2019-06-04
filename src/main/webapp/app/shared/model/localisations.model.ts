import { IEquipement } from 'app/shared/model/equipement.model';

export interface ILocalisations {
    id?: number;
    nomlocalisation?: string;
    localisationeEquipements?: IEquipement[];
}

export class Localisations implements ILocalisations {
    constructor(public id?: number, public nomlocalisation?: string, public localisationeEquipements?: IEquipement[]) {}
}
