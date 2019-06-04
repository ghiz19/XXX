/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { DemandeinterventionService } from 'app/entities/demandeintervention/demandeintervention.service';
import { IDemandeintervention, Demandeintervention } from 'app/shared/model/demandeintervention.model';

describe('Service Tests', () => {
    describe('Demandeintervention Service', () => {
        let injector: TestBed;
        let service: DemandeinterventionService;
        let httpMock: HttpTestingController;
        let elemDefault: IDemandeintervention;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(DemandeinterventionService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Demandeintervention(0, 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        datedemande: currentDate.format(DATE_FORMAT)
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

            it('should create a Demandeintervention', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        datedemande: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        datedemande: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Demandeintervention(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Demandeintervention', async () => {
                const returnedFromService = Object.assign(
                    {
                        description: 'BBBBBB',
                        degreeurgence: 'BBBBBB',
                        datedemande: currentDate.format(DATE_FORMAT),
                        priorite: 'BBBBBB',
                        dateprevu: 'BBBBBB',
                        datereel: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        datedemande: currentDate
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

            it('should return a list of Demandeintervention', async () => {
                const returnedFromService = Object.assign(
                    {
                        description: 'BBBBBB',
                        degreeurgence: 'BBBBBB',
                        datedemande: currentDate.format(DATE_FORMAT),
                        priorite: 'BBBBBB',
                        dateprevu: 'BBBBBB',
                        datereel: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        datedemande: currentDate
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

            it('should delete a Demandeintervention', async () => {
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
