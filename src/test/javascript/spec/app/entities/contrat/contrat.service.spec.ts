/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { ContratService } from 'app/entities/contrat/contrat.service';
import { IContrat, Contrat } from 'app/shared/model/contrat.model';

describe('Service Tests', () => {
    describe('Contrat Service', () => {
        let injector: TestBed;
        let service: ContratService;
        let httpMock: HttpTestingController;
        let elemDefault: IContrat;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ContratService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Contrat(0, 'AAAAAAA', 0, 0, currentDate, currentDate, currentDate);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        datedebutcontrat: currentDate.format(DATE_FORMAT),
                        datefincontrat: currentDate.format(DATE_FORMAT),
                        daterealisation: currentDate.format(DATE_FORMAT)
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

            it('should create a Contrat', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        datedebutcontrat: currentDate.format(DATE_FORMAT),
                        datefincontrat: currentDate.format(DATE_FORMAT),
                        daterealisation: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        datedebutcontrat: currentDate,
                        datefincontrat: currentDate,
                        daterealisation: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Contrat(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Contrat', async () => {
                const returnedFromService = Object.assign(
                    {
                        nomcontrat: 'BBBBBB',
                        coutcontrat: 1,
                        numerocontrat: 1,
                        datedebutcontrat: currentDate.format(DATE_FORMAT),
                        datefincontrat: currentDate.format(DATE_FORMAT),
                        daterealisation: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        datedebutcontrat: currentDate,
                        datefincontrat: currentDate,
                        daterealisation: currentDate
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

            it('should return a list of Contrat', async () => {
                const returnedFromService = Object.assign(
                    {
                        nomcontrat: 'BBBBBB',
                        coutcontrat: 1,
                        numerocontrat: 1,
                        datedebutcontrat: currentDate.format(DATE_FORMAT),
                        datefincontrat: currentDate.format(DATE_FORMAT),
                        daterealisation: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        datedebutcontrat: currentDate,
                        datefincontrat: currentDate,
                        daterealisation: currentDate
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

            it('should delete a Contrat', async () => {
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
