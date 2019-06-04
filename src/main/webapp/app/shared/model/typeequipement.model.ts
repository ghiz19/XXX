import { IEquipement } from 'app/shared/model/equipement.model';

export interface ITypeequipement {
    id?: number;
    typeequipem?: string;
    equipments?: IEquipement[];
}

export class Typeequipement implements ITypeequipement {
    constructor(public id?: number, public typeequipem?: string, public equipments?: IEquipement[]) {}
}
