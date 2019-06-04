/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GmaovTestModule } from '../../../test.module';
import { HistoriquetacheUpdateComponent } from 'app/entities/historiquetache/historiquetache-update.component';
import { HistoriquetacheService } from 'app/entities/historiquetache/historiquetache.service';
import { Historiquetache } from 'app/shared/model/historiquetache.model';

describe('Component Tests', () => {
    describe('Historiquetache Management Update Component', () => {
        let comp: HistoriquetacheUpdateComponent;
        let fixture: ComponentFixture<HistoriquetacheUpdateComponent>;
        let service: HistoriquetacheService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [HistoriquetacheUpdateComponent]
            })
                .overrideTemplate(HistoriquetacheUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(HistoriquetacheUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HistoriquetacheService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Historiquetache(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.historiquetache = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Historiquetache();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.historiquetache = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
