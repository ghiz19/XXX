/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { EquipementService } from 'app/entities/equipement/equipement.service';
import { IEquipement, Equipement } from 'app/shared/model/equipement.model';

describe('Service Tests', () => {
    describe('Equipement Service', () => {
        let injector: TestBed;
        let service: EquipementService;
        let httpMock: HttpTestingController;
        let elemDefault: IEquipement;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(EquipementService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Equipement(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                currentDate,
                currentDate,
                'AAAAAAA',
                0,
                0,
                0,
                'image/png',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        dateAchat: currentDate.format(DATE_FORMAT),
                        datedernieremaintenance: currentDate.format(DATE_FORMAT),
                        dateexperation: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Equipement', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        dateAchat: currentDate.format(DATE_FORMAT),
                        datedernieremaintenance: currentDate.format(DATE_FORMAT),
                        dateexperation: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateAchat: currentDate,
                        datedernieremaintenance: currentDate,
                        dateexperation: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Equipement(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Equipement', async () => {
                const returnedFromService = Object.assign(
                    {
                        nomEquipement: 'BBBBBB',
                        marque: 'BBBBBB',
                        description: 'BBBBBB',
                        dateAchat: currentDate.format(DATE_FORMAT),
                        datedernieremaintenance: currentDate.format(DATE_FORMAT),
                        dateexperation: currentDate.format(DATE_FORMAT),
                        fabricant: 'BBBBBB',
                        prixAchat: 1,
                        numeroSerie: 1,
                        numeroCommande: 1,
                        image: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        dateAchat: currentDate,
                        datedernieremaintenance: currentDate,
                        dateexperation: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Equipement', async () => {
                const returnedFromService = Object.assign(
                    {
                        nomEquipement: 'BBBBBB',
                        marque: 'BBBBBB',
                        description: 'BBBBBB',
                        dateAchat: currentDate.format(DATE_FORMAT),
                        datedernieremaintenance: currentDate.format(DATE_FORMAT),
                        dateexperation: currentDate.format(DATE_FORMAT),
                        fabricant: 'BBBBBB',
                        prixAchat: 1,
                        numeroSerie: 1,
                        numeroCommande: 1,
                        image: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateAchat: currentDate,
                        datedernieremaintenance: currentDate,
                        dateexperation: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Equipement', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
