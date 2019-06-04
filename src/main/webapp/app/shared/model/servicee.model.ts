import { IEquipement } from 'app/shared/model/equipement.model';

export interface IServicee {
    id?: number;
    nomService?: string;
    equipems?: IEquipement[];
}

export class Servicee implements IServicee {
    constructor(public id?: number, public nomService?: string, public equipems?: IEquipement[]) {}
}
